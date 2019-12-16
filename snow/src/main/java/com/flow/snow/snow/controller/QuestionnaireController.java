package com.flow.snow.snow.controller;

import com.flow.snow.snow.entity.Questionnaire;
import com.flow.snow.snow.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quest")
public class QuestionnaireController {
    @Autowired
    QuestionnaireService questionnaireService;

    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    public void insert(Questionnaire questionnaire) {
        questionnaireService.insert(questionnaire);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<Questionnaire> getAll(String type, String openid) {
        return questionnaireService.getAll(type, openid);
    }

    @RequestMapping(path = "/getById", method = RequestMethod.GET)
    public Questionnaire getById(Long id) {
        return questionnaireService.getById(id);
    }

}
