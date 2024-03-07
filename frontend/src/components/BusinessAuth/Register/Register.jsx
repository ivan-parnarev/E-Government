import { Button, FloatingLabel, Form } from 'react-bootstrap';
import styles from './Register.module.css';
import { useState } from 'react';
import { Link } from 'react-router-dom';
import validatePassword from '../../../utils/validateAuth';

export default function Register() {
    const [formData, setFormData] = useState({
        username: '',
        password: '',
        repeatPassword: '',
    });
    const [errors, setErrors] = useState([]);

    const onChange = (e) => {
        setErrors([]);
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const resetFileds = () => {
        setFormData({
            username: '',
            password: '',
            repeatPassword: '',
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(formData);
        const valid = validatePassword(formData.password, formData.repeatPassword);
        if (!valid.success) {
            setErrors(valid.errors);
            resetFileds();
            return;
        }
        console.log(valid);
        resetFileds();
    };

    return (
        <div>
            <form onSubmit={handleSubmit} className={styles.form}>
                <h1 className={styles.title}>Регистрирай се</h1>

                {errors.map((error) => (
                    <p key={error} className={styles.error}>
                        {error}
                    </p>
                ))}

                <FloatingLabel
                    controlId="username"
                    label="Потребителско име"
                    className={`mb-3 ${styles.label}`}
                >
                    <Form.Control
                        type="text"
                        name="username"
                        placeholder="Потребителско име..."
                        onChange={onChange}
                        value={formData.username}
                    />
                </FloatingLabel>

                <FloatingLabel
                    controlId="password"
                    label="Парола"
                    className={`mb-3 ${styles.label}`}
                >
                    <Form.Control
                        name="password"
                        type="password"
                        placeholder="Парола..."
                        onChange={onChange}
                        value={formData.password}
                    />
                </FloatingLabel>

                <FloatingLabel
                    controlId="passeor2"
                    label="Повтори паролата"
                    className={`mb-3 ${styles.label}`}
                >
                    <Form.Control
                        name="repeatPassword"
                        type="password"
                        placeholder="Повтори паролата..."
                        onChange={onChange}
                        value={formData.repeatPassword}
                    />
                </FloatingLabel>

                <Button className={styles.button} type="submit">
                    Регистрирай се
                </Button>
            </form>

            <p className={styles.login}>
                Имаш аканут? Влез от <Link to={'/business/login'}>тук</Link>.
            </p>
        </div>
    );
}
