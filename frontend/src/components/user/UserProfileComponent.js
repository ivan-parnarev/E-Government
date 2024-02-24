import { useEffect, useState } from 'react';

import styles from './UserProfileComponent.module.css';

import Section from '../custom-elements/Section/Section';
import Campaign from '../Campaign/Campaign';

const fakeNews = [
    {
        id: 1,
        title: 'Инфлацията нараства с всеки изминал ден.',
        source: 'BTV News'
    },
    {
        id: 2,
        title: 'Партия Х спечели изборите.',
        source: 'Х news'
    },
    {
        id: 3,
        title: 'Нов скандал! Еди кой си направи нещо!',
        source: 'Скандал.бг'
    },
    {
        id: 4,
        title: 'Държавата увеличава цената на винетките.',
        source: 'Греда.бг'
    },
    {
        id: 5,
        title: 'Избори през март.',
        source: 'Утро.бг'
    },
    {
        id: 6,
        title: 'Ще има ли нов президент?',
        source: 'Новините.бг'
    },
    {
        id: 7,
        title: 'Шенген, за или против?',
        source: 'Новости.бг'
    },
    {
        id: 8,
        title: 'Повишаване на пенсиите. Как живеят българските пенсионери?',
        source: 'Делник.бг'
    },
    {
        id: 9,
        title: 'Цените на горивата поскъпват!',
        source: 'Гориво.бг'
    },
    {
        id: 10,
        title: 'Как се отразява войанта на икономиката?',
        source: 'Интересно.бг'
    },
    {
        id: 11,
        title: 'Скандал! Хванаха известна личност зад волана след употреба на алкохол.',
        source: 'Скандал.бг'
    },
];

export default function UserProfileComponent() {
    const [campaigns, setCampaigns] = useState([]);

    useEffect(() => {
        const filteredCampaigns = JSON.parse(localStorage.getItem('filteredCampaigns'));

        setCampaigns((state) => [...filteredCampaigns]);
    }, [])

    return (
        <section className={styles['profile-section']}>
            <Section className={styles['user-info']}>
                <div className={styles['user-info-wrapper']}>
                    <div className={styles['core-data']}>
                        <p className={styles['username']}><span className={styles['name-wrapper']}>Име:</span>Пешо Пешов</p>
                        <p className={styles['birth-date']}><span className={styles['birth-date-wrapper']}>Дата на раждане:</span>10/10/1996</p>
                        <p className={styles['gender']}><span className={styles['gender-wrapper']}>Пол:</span>Мъж</p>
                    </div>
                    <div className={styles['secondary-data']}>
                        <p className={styles['country']}><span className={styles['country-wrapper']}>Страна:</span>България</p>
                        <p className={styles['city']}><span className={styles['city-wrapper']}>Град:</span>София</p>
                        <p className={styles['phone-number']}><span className={styles['phone-number-wrapper']}>Телефон:</span>0001112223</p>
                    </div>
                </div>
                <p className={styles['edit-button']}>Редактирай</p>
            </Section>

            <Section title='Изминали кампании' className={styles['active-campaigns']}>
                <div className={styles['campaigns-wrapper']}>
                    {campaigns.map((campaign, index) => (
                        <Campaign key={index} {...campaign} />
                    ))}
                </div>
            </Section>

            <Section title='Активни кампании' className={styles['active-campaigns']}>
                <div className={styles['campaigns-wrapper']}>
                    {campaigns.map((campaign, index) => (
                        <Campaign key={index} {...campaign} />
                    ))}
                </div>
            </Section>

            <Section title='Предстоящи кампании' className={styles['future-campaigns']}> 
                <div className={styles['campaigns-wrapper']}>
                    {campaigns.map((campaign, index) => (
                        <Campaign key={index} {...campaign} />
                    ))}
                </div>
            </Section>

            <Section className={styles['news']}>
                <div className={styles['news-wrapper']}>
                        {fakeNews.map((currentNews) => (
                            <article key={currentNews.id} className={styles['current-news-wrapper']}>
                                <div className={styles['media-wrapper']}>
                                    <img src='/news.jpg' alt="news-image" />
                                </div>
                                <p className={styles['title-wrapper']}>{currentNews.title}</p>
                                <p className={styles['source-wrapper']}>{currentNews.source}</p>
                            </article>
                        ))}
                </div>
            </Section>
        </section>
    );
}