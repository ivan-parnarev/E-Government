import { Button, FloatingLabel, Form } from 'react-bootstrap';
import styles from './Login.module.css';
import { FaFacebook, FaGoogle } from 'react-icons/fa';
import { useState } from 'react';
import { Link } from 'react-router-dom';

export default function Login() {
    const [formData, setFormData] = useState({
        username: '',
        password: '',
    });

    const onChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(formData);
        setFormData({
            username: '',
            password: '',
        });
    };

    return (
        <div>
            <form onSubmit={handleSubmit} className={styles.form}>
                <h1 className={styles.title}>Влизане</h1>

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

                <Button className={styles.button} type="submit">
                    Влез
                </Button>
                <p className={styles.or}>или</p>
                <div className={styles.socials}>
                    <FaFacebook fill="white" size={40} cursor={'pointer'} />
                    <FaGoogle fill="white" size={40} cursor={'pointer'} />
                </div>
            </form>

            <div className={styles.utils}>
                <Form.Check
                    type="checkbox"
                    id="remember"
                    name="remember"
                    label="Запомни ме"
                    className={`mb-0 ${styles.checkLabel}`}
                />
                <p className={styles.resetPwd}>
                    <a href="#">Забравена парола?</a>
                </p>
            </div>

            <p className={styles.register}>
                Нямаш аканут? Регистрирай се <Link to={'/business/register'}>тук</Link>.
            </p>
        </div>
    );
}
