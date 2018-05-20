package elte.homework.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findQuestionsByClient(Client clien);
    List<Question> findQuestionsByTopic(Topic topic);
}
