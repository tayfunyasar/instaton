package com.instaton.config.web;

import java.io.IOException;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	ResourceProperties resourceProperties = new ResourceProperties();

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {

		if (!registry.hasMappingForPattern("/assets/**")) {
			registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
		}

		if (!registry.hasMappingForPattern("/app/**")) {
			registry.addResourceHandler("/app/**").addResourceLocations("/app/");
		}

		if (!registry.hasMappingForPattern("/**/index.html")) {
			registry.addResourceHandler("/**/index.html").addResourceLocations("/index.html");
		}

		if (!registry.hasMappingForPattern("/logon/**")) {
			registry.addResourceHandler("/logon/**").addResourceLocations("/logon/");
		}

		final Integer cachePeriod = Integer.valueOf(10/* environment.getProperty("spring.resources.cache-period") */);

		final String[] staticLocations = this.resourceProperties.getStaticLocations();
		final String[] indexLocations = new String[staticLocations.length];
		for (int i = 0; i < staticLocations.length; i++) {
			indexLocations[i] = staticLocations[i] + "index.html";
		}
		registry.addResourceHandler("/**/*.css", "/**/*.html", "/**/*.js", "/**/*.json", "/**/*.bmp", "/**/*.jpeg", "/**/*.jpg", "/**/*.png", "/**/*.ttf", "/**/*.eot", "/**/*.svg", "/**/*.woff", "/**/*.woff2").addResourceLocations(staticLocations).setCachePeriod(cachePeriod);

		registry.addResourceHandler("/**").addResourceLocations(indexLocations).setCachePeriod(cachePeriod).resourceChain(true).addResolver(new PathResourceResolver() {

			@Override
			protected Resource getResource(final String resourcePath, final Resource location) throws IOException {
				return location.exists() && location.isReadable() ? location : null;
			}
		});

	}

	@Override
	public void configureViewResolvers(final ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/jsp/", ".jsp");
	}

}