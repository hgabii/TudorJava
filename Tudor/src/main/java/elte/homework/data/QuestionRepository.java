package elte.homework.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
