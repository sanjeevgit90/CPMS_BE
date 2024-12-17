package aurionpro.erp.ipms.healthapp.selfassessment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionnaireRepository extends JpaRepository<Questionnaires,Long>{

    List<Questionnaires> findByQuestionbankname(String questionbankname);

}