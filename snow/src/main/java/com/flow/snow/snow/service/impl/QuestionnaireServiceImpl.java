package com.flow.snow.snow.service.impl;

import com.flow.snow.snow.dao.QuesttionnaireDao;
import com.flow.snow.snow.entity.Questionnaire;
import com.flow.snow.snow.mapper.QuesttionnaireMapper;
import com.flow.snow.snow.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired
    QuesttionnaireMapper questtionnaireMapper;
    @Autowired
    QuesttionnaireDao questtionnaireDao;

    @Override
    public void insert(Questionnaire questionnaire) {
        questtionnaireMapper.insert(questionnaire);
    }

    @Override
    public List<Questionnaire> getAll(String type, String openid) {
        return questtionnaireDao.findQuestionnaireByTypeAndOpenid(type, openid);
    }

    @Override
    public Questionnaire getById(Long id) {
        return questtionnaireDao.findQuestionnaireById(id);
    }
}
