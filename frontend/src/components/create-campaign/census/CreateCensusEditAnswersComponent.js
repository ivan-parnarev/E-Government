import Button from 'react-bootstrap/Button';
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import InputGroup from 'react-bootstrap/InputGroup';
import Form from 'react-bootstrap/Form';
import styles from './CreateCensusEditAnswersComponent.module.css';
import { useState } from 'react';

export function CreateCensusEditAnswersComponent({
    censusCategories,
    censusQuestions,
    setCensusQuestions,
}) {
    const [selectedCategory, setSelectedCategory] = useState('');
    const [editingAnswer, setEditingAnswer] = useState({
        question: '',
        answer: '',
    });
    const [editedAnswer, setEditedAnswer] = useState('');

    const handleCategoryButtonClick = (category) => {
        setSelectedCategory(category);
    };

    const handleEditAnswer = (questionText, answerText) => {
        setEditingAnswer({ question: questionText, answer: answerText });
        setEditedAnswer(answerText);
    };

    const handleCancelEdit = () => {
        setEditingAnswer({ question: '', answer: '' });
        setEditedAnswer('');
    };

    const handleSaveEdit = () => {
        const updatedCensusQuestions = censusQuestions.map((question) => {
            if (question.text === editingAnswer.question) {
                const updatedAnswers = question.answers.map((answer) =>
                    answer.answerText === editingAnswer.answer
                        ? { ...answer, answerText: editedAnswer }
                        : answer
                );

                return { ...question, answers: updatedAnswers };
            }
            return question;
        });

        setCensusQuestions(updatedCensusQuestions);
        setEditingAnswer({ question: '', answer: '' });
        setEditedAnswer('');
    };

    return (
        <div>
            <p>Въпроси:</p>

            <ButtonGroup className={styles.buttonGroupCategories}>
                {censusCategories.map((category) => (
                    <Button
                        key={category}
                        onClick={() => handleCategoryButtonClick(category)}
                        variant={selectedCategory === category ? 'primary' : 'secondary'}
                    >
                        {category}
                    </Button>
                ))}
            </ButtonGroup>

            <div className={styles.censusCategoryInfoContainer}>
                {censusQuestions
                    .filter((question) => question.questionCategory === selectedCategory)
                    .map(({ text, questionCategory, answers }) => {
                        return (
                            <InputGroup className={styles.inputGroup} key={text}>
                                <p className={styles.inputGroupInputLabel}>
                                    <b>{text}</b>
                                </p>
                                <Form>
                                    {answers.map((answer) => (
                                        <div key={answer.answerText}>
                                            {editingAnswer.question === text &&
                                            editingAnswer.answer === answer.answerText ? (
                                                <InputGroup className={styles.editingInputGroup}>
                                                    <Button
                                                        className={styles.inputGroupButton}
                                                        onClick={handleSaveEdit}
                                                    >
                                                        ✔️
                                                    </Button>
                                                    <Form.Control
                                                        type="text"
                                                        value={editedAnswer}
                                                        onChange={(e) =>
                                                            setEditedAnswer(e.target.value)
                                                        }
                                                    />

                                                    <Button
                                                        className={styles.inputGroupButton}
                                                        onClick={handleCancelEdit}
                                                    >
                                                        ❌
                                                    </Button>
                                                </InputGroup>
                                            ) : (
                                                <p key={answer.answerText}>
                                                    <Button
                                                        className={styles.inputGroupButton}
                                                        onClick={() =>
                                                            handleEditAnswer(
                                                                text,
                                                                answer.answerText
                                                            )
                                                        }
                                                    >
                                                        ✏️
                                                    </Button>{' '}
                                                    {answer.answerText}
                                                </p>
                                            )}
                                        </div>
                                    ))}
                                </Form>
                            </InputGroup>
                        );
                    })}
            </div>
        </div>
    );
}
