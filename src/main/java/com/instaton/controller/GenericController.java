package com.instaton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.instaton.constant.EndpointConstant;
import com.instaton.entity.generic.parameter.ParameterListInputData;
import com.instaton.entity.generic.parameter.ParameterListOutput;
import com.instaton.exception.InstatonException;
import com.instaton.service.GenericService;

@RestController
@RequestMapping(EndpointConstant.API_ENDPOINT_GENERIC_PATTERN)
public class GenericController {

	@Autowired
	private GenericService genericService;

	@RequestMapping(value = "/parameters", method = RequestMethod.POST)
	public ParameterListOutput getParameterList(@RequestBody ParameterListInputData inputData) throws InstatonException {

		ParameterListOutput parameterList = this.genericService.getParameterList(inputData);

		return parameterList;
	}
}
