package com.instaton.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@EnableWebMvc
public class WebMvcResourcesConfiguration extends WebMvcConfigurerAdapter {

	ResourceProperties resourceProperties = new ResourceProperties();

	// http://haacked.com/archive/2008/11/20/anatomy-of-a-subtle-json-vulnerability.aspx
	// JSON Vulnerability Protection
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converters.add(converter);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (!registry.hasMappingForPattern("/**/index.html")) {
			registry.addResourceHandler("/**/index.html").addResourceLocations("/index.html");
		}

		Integer cachePeriod = Integer.valueOf(10/* environment.getProperty("spring.resources.cache-period") */);

		final String[] staticLocations = resourceProperties.getStaticLocations();
		final String[] indexLocations = new String[staticLocations.length];
		for (int i = 0; i < staticLocations.length; i++) {
			indexLocations[i] = staticLocations[i] + "index.html";
		}
		registry.addResourceHandler("/**/*.css", "/**/*.html", "/**/*.js", "/**/*.json", "/**/*.bmp", "/**/*.jpeg", "/**/*.jpg", "/**/*.png", "/**/*.ttf", "/**/*.eot", "/**/*.svg", "/**/*.woff", "/**/*.woff2").addResourceLocations(staticLocations).setCachePeriod(cachePeriod);

		registry.addResourceHandler("/**").addResourceLocations(indexLocations).setCachePeriod(cachePeriod).resourceChain(true).addResolver(new PathResourceResolver() {

			@Override
			protected Resource getResource(String resourcePath, Resource location) throws IOException {
				return location.exists() && location.isReadable() ? location : null;
			}
		});
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("60MB");
		factory.setMaxRequestSize("60MB");
		return factory.createMultipartConfig();
	}

}
