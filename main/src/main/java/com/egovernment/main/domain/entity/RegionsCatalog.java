package com.egovernment.main.domain.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionsCatalog {
    private List<Region> regions = new ArrayList<>();

    public Optional<Region> findRegionById(int regionId) {
        return regions.stream()
                .filter(region -> region.getId() == regionId)
                .findFirst();
    }

    public String translateRegionNameToBulgarian(String regionName) {
        String translatedName;

        switch (regionName) {
            case "global":
                translatedName = "Глобална";
                break;
            case "blagoevgrad":
                translatedName = "Благоевград";
                break;
            case "burgas":
                translatedName = "Бургас";
                break;
            case "varna":
                translatedName = "Варна";
                break;
            case "velikoTarnovo":
                translatedName = "Велико Търново";
                break;
            case "vidin":
                translatedName = "Видин";
                break;
            case "vratsa":
                translatedName = "Враца";
                break;
            case "gabrovo":
                translatedName = "Габрово";
                break;
            case "dobrich":
                translatedName = "Добрич";
                break;
            case "kardzhali":
                translatedName = "Кърджали";
                break;
            case "kyustendil":
                translatedName = "Кюстендил";
                break;
            case "lovech":
                translatedName = "Ловеч";
                break;
            case "montana":
                translatedName = "Монтана";
                break;
            case "pazardzhik":
                translatedName = "Пазарджик";
                break;
            case "pernik":
                translatedName = "Перник";
                break;
            case "pleven":
                translatedName = "Плевен";
                break;
            case "plovdiv":
                translatedName = "Пловдив";
                break;
            case "razgrad":
                translatedName = "Разград";
                break;
            case "ruse":
                translatedName = "Русе";
                break;
            case "silistra":
                translatedName = "Силистра";
                break;
            case "sliven":
                translatedName = "Сливен";
                break;
            case "smolyan":
                translatedName = "Смолян";
                break;
            case "sofia":
                translatedName = "София";
                break;
            case "sofiaProvince":
                translatedName = "Софийска";
                break;
            case "staraZagora":
                translatedName = "Стара Загора";
                break;
            case "targovishte":
                translatedName = "Търговище";
                break;
            case "haskovo":
                translatedName = "Хасково";
                break;
            case "shumen":
                translatedName = "Шумен";
                break;
            case "yambol":
                translatedName = "Ямбол";
                break;
            default:
                translatedName = "Unknown";
                break;
        }

        return translatedName;

    }

}
