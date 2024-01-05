package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.domain.dto.region.RegionDTO;
import com.egovernment.egovbackend.domain.entity.Region;
import com.egovernment.egovbackend.domain.entity.RegionsCatalog;
import com.egovernment.egovbackend.domain.factory.region.RegionFactory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionFactory regionFactory = new RegionFactory();
    private final ModelMapper modelMapper;

    public void initRegions() {

        if (RegionsCatalog.getInstance().getRegions().size() == 0) {

            Region global = this.regionFactory
                    .createRegion(1, "global", "Глобална");

            Region blagoevgrad = this.regionFactory
                    .createRegion(2700, "blagoevgrad", "Благоевград");

            Region burgas = this.regionFactory
                    .createRegion(8000, "burgas", "Бургас");

            Region varna = this.regionFactory
                    .createRegion(9000, "varna", "Варна");

            Region velikoTarnovo = this.regionFactory
                    .createRegion(5000, "velikoTarnovo", "Велико Търново");

            Region vidin = this.regionFactory
                    .createRegion(3700, "vidin", "Видин");

            Region vratsa = this.regionFactory
                    .createRegion(3000, "vratsa", "Враца");

            Region gabrovo = this.regionFactory
                    .createRegion(5300, "gabrovo", "Габрово");

            Region dobrich = this.regionFactory
                    .createRegion(9300, "dobrich", "Добрич");

            Region kardzhali = this.regionFactory
                    .createRegion(6600, "kardzhali", "Кърджали");

            Region kyustendil = this.regionFactory
                    .createRegion(2500, "kyustendil", "Кюстендил");

            Region lovech = this.regionFactory
                    .createRegion(5500, "lovech", "Ловеч");

            Region montana = this.regionFactory
                    .createRegion(3400, "montana", "Монтана");

            Region pazardzhik = this.regionFactory
                    .createRegion(4400, "pazardzhik", "Пазарджик");

            Region pernik = this.regionFactory
                    .createRegion(2300, "pernik", "Перник");

            Region pleven = this.regionFactory
                    .createRegion(5800, "pleven", "Плевен");

            Region plovdiv = this.regionFactory
                    .createRegion(4000, "plovdiv", "Пловдив");

            Region razgrad = this.regionFactory
                    .createRegion(7200, "razgrad", "Разград");

            Region ruse = this.regionFactory
                    .createRegion(7000, "ruse", "Русе");

            Region silistra = this.regionFactory
                    .createRegion(7500, "silistra", "Силистра");

            Region sliven = this.regionFactory
                    .createRegion(8800, "sliven", "Сливен");

            Region smolyan = this.regionFactory
                    .createRegion(4700, "smolyan", "Смолян");

            Region sofia = this.regionFactory
                    .createRegion(1000, "sofia", "София");

//            Region sofiaProvince = this.regionFactory
//                    .createRegion(24, "sofiaProvince", "София Област");

            Region staraZagora = this.regionFactory
                    .createRegion(6000, "staraZagora", "Стара Загора");

            Region targovishte = this.regionFactory
                    .createRegion(7700, "targovishte", "Търговище");

            Region haskovo = this.regionFactory
                    .createRegion(6300, "haskovo", "Хасково");

            Region shumen = this.regionFactory
                    .createRegion(9700, "shumen", "Шумен");

            Region yambol = this.regionFactory
                    .createRegion(8600, "yambol", "Ямбол");

            List<Region> regions = List.of(global, blagoevgrad, burgas, varna,
                    velikoTarnovo, vidin, vratsa, gabrovo, dobrich, kardzhali,
                    kyustendil, lovech, montana, pazardzhik, pernik, pleven,
                    plovdiv, razgrad, ruse, silistra, sliven, smolyan, sofia,
                    staraZagora, targovishte, haskovo, shumen, yambol);

            RegionsCatalog.getInstance().setRegions(regions);

        }

    }

    public Optional<Region> getRegionByEnglishName(String englishName) {
        return RegionsCatalog.getInstance().findRegionByEnglishName(englishName);
    }

    public Optional<Region> getRegionByBulgarianName(String bulgarianName) {
        return RegionsCatalog.getInstance().findRegionByBulgarianName(bulgarianName);
    }

    public Optional<Region> getRegionById(int regionId) {
        return RegionsCatalog.getInstance().findRegionById(regionId);
    }

    public List<RegionDTO> getAllRegions() {

        return RegionsCatalog.getInstance().getRegions()
                .stream()
                .map(r -> this.modelMapper.map(r, RegionDTO.class))
                .collect(Collectors.toList());
    }
}