package com.flow.snow.snow.service;

import com.flow.snow.snow.entity.Questionnaire;

import java.util.List;

public interface QuestionnaireService {
    void insert(Questionnaire questionnaire);
    List<Questionnaire> getAll(String type, String openid);
    Questionnaire getById(Long id);
}
