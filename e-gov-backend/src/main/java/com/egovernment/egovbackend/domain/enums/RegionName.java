package com.egovernment.egovbackend.domain.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RegionName {
    GLOBAL("Глобално"),
    BLAGOEVGRAD("Благоевград"),
    BURGAS("Бургас"),
    VARNA("Варна"),
    VELIKO_TARNOVO("Велико Търново"),
    VIDIN("Видин"),
    VRATSA("Враца"),
    GABROVO("Габрово"),
    DOBRICH("Добрич"),
    KARDJALI("Кърджали"),
    KYUSTENDIL("Кюстендил"),
    LOVECH("Ловеч"),
    MONTANA("Монтана"),
    PAZARDJIK("Пазарджик"),
    PERNIK("Перник"),
    PLEVEN("Плевен"),
    PLOVDIV("Пловдив"),
    RUSE("Русе"),
    SILISTRA("Силистра"),
    SLIVEN("Сливен"),
    SMOLYAN("Смолян"),
    SOFIA("София"),
    SOFIA_PROVINCE("София Област"),
    STARA_ZAGORA("Стара Загора"),
    TARGOVISHTE("Търговище"),
    HASKOVO("Хасково"),
    SHUMEN("Шумен"),
    YAMBOL("Ямбол");

    private final String bulgarianTranslation;

    public String getBulgarianTranslation() {
        return bulgarianTranslation;
    }

}
