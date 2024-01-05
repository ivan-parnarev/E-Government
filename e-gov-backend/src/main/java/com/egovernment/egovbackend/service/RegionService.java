package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.entity.Region;
import com.egovernment.egovbackend.domain.entity.RegionsCatalog;
import com.egovernment.egovbackend.domain.factory.region.RegionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private final RegionFactory regionFactory = new RegionFactory();

    public void initRegions(){

        if(RegionsCatalog.getInstance().getRegions().size() == 0){


            Region global = this.regionFactory
                    .createRegion((byte) 1, "global", "Глобална");

            Region blagoevgrad = this.regionFactory
                    .createRegion((byte) 2, "blagoevgrad", "Благоевград");

            Region burgas = this.regionFactory
                    .createRegion((byte) 3, "burgas", "Бургас");

            Region varna = this.regionFactory
                    .createRegion((byte) 4, "varna", "Варна");

            Region velikoTarnovo = this.regionFactory
                    .createRegion((byte) 5, "velikoTarnovo", "Велико Търново");

            Region vidin = this.regionFactory
                    .createRegion((byte) 6, "vidin", "Видин");

            Region vratsa = this.regionFactory
                    .createRegion((byte) 7, "vratsa", "Враца");

            Region gabrovo = this.regionFactory
                    .createRegion((byte) 8, "gabrovo", "Габрово");

            Region dobrich = this.regionFactory
                    .createRegion((byte) 9, "dobrich", "Добрич");

            Region kardzhali = this.regionFactory
                    .createRegion((byte) 10, "kardzhali", "Кърджали");

            Region kyustendil = this.regionFactory
                    .createRegion((byte) 11, "kyustendil", "Кюстендил");

            Region lovech = this.regionFactory
                    .createRegion((byte) 12, "lovech", "Ловеч");

            Region montana = this.regionFactory
                    .createRegion((byte) 13, "montana", "Монтана");

            Region pazardzhik = this.regionFactory
                    .createRegion((byte) 14, "pazardzhik", "Пазарджик");

            Region pernik = this.regionFactory
                    .createRegion((byte) 15, "pernik", "Перник");

            Region pleven = this.regionFactory
                    .createRegion((byte) 16, "pleven", "Плевен");

            Region plovdiv = this.regionFactory
                    .createRegion((byte) 17, "plovdiv", "Пловдив");

            Region razgrad = this.regionFactory
                    .createRegion((byte) 18, "razgrad", "Разград");

            Region ruse = this.regionFactory
                    .createRegion((byte) 19, "ruse", "Русе");

            Region silistra = this.regionFactory
                    .createRegion((byte) 20, "silistra", "Силистра");

            Region sliven = this.regionFactory
                    .createRegion((byte) 21, "sliven", "Сливен");

            Region smolyan = this.regionFactory
                    .createRegion((byte) 22, "smolyan", "Смолян");

            Region sofia = this.regionFactory
                    .createRegion((byte) 23, "sofia", "София");

            Region sofiaProvince = this.regionFactory
                    .createRegion((byte) 24, "sofiaProvince", "София Област");

            Region staraZagora = this.regionFactory
                    .createRegion((byte) 25, "staraZagora", "Стара Загора");

            Region targovishte = this.regionFactory
                    .createRegion((byte) 26, "targovishte", "Търговище");

            Region haskovo = this.regionFactory
                    .createRegion((byte) 27, "haskovo", "Хасково");

            Region shumen = this.regionFactory
                    .createRegion((byte) 28, "shumen", "Шумен");

            Region yambol = this.regionFactory
                    .createRegion((byte) 29, "yambol", "Ямбол");

            List<Region> regions = List.of(global, blagoevgrad, burgas, varna,
                    velikoTarnovo, vidin, vratsa, gabrovo, dobrich, kardzhali,
                    kyustendil, lovech, montana, pazardzhik, pernik, pleven,
                    plovdiv, razgrad, ruse, silistra, sliven, smolyan, sofia, sofiaProvince,
                    staraZagora, targovishte, haskovo, shumen, yambol);

            RegionsCatalog.getInstance().setRegions(regions);

        }

    }

}
