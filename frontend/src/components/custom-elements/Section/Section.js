import styles from './Section.module.css';

export default function Section({title, children, ...props}) {
    return (
            <section {...props}>
                {title && <h2 className={styles['section-heading']}>{title}</h2>}
                {children}
            </section>
    );
}