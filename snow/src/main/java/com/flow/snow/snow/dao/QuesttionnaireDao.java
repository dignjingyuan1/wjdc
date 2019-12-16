package com.flow.snow.snow.dao;

import com.flow.snow.snow.entity.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuesttionnaireDao extends JpaRepository<Questionnaire, Long> {
    List<Questionnaire> findQuestionnaireByTypeAndOpenid(String type, String openid);
    Questionnaire findQuestionnaireById(Long id);
}
