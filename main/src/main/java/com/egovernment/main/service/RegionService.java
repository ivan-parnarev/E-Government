package com.egovernment.main.service;

import com.egovernment.main.domain.dto.region.RegionDTO;
import com.egovernment.main.domain.entity.Region;
import com.egovernment.main.domain.entity.RegionsCatalog;
import com.egovernment.main.domain.factory.region.RegionFactory;
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


    private final RegionsCatalog regionsCatalog;

    public void initRegions() {

        if (regionsCatalog.getRegions().size() == 0) {

            Region global = this.regionFactory.createRegion(1, "global");

            Region blagoevgrad = this.regionFactory
                    .createRegion(2700, "blagoevgrad");

            Region burgas = this.regionFactory
                    .createRegion(8000, "burgas");

            Region varna = this.regionFactory
                    .createRegion(9000, "varna");

            Region velikoTarnovo = this.regionFactory
                    .createRegion(5000, "velikoTarnovo");

            Region vidin = this.regionFactory
                    .createRegion(3700, "vidin");

            Region vratsa = this.regionFactory
                    .createRegion(3000, "vratsa");

            Region gabrovo = this.regionFactory
                    .createRegion(5300, "gabrovo");

            Region dobrich = this.regionFactory
                    .createRegion(9300, "dobrich");

            Region kardzhali = this.regionFactory
                    .createRegion(6600, "kardzhali");

            Region kyustendil = this.regionFactory
                    .createRegion(2500, "kyustendil");

            Region lovech = this.regionFactory
                    .createRegion(5500, "lovech");

            Region montana = this.regionFactory
                    .createRegion(3400, "montana");

            Region pazardzhik = this.regionFactory
                    .createRegion(4400, "pazardzhik");

            Region pernik = this.regionFactory
                    .createRegion(2300, "pernik");

            Region pleven = this.regionFactory
                    .createRegion(5800, "pleven");

            Region plovdiv = this.regionFactory
                    .createRegion(4000, "plovdiv");

            Region razgrad = this.regionFactory
                    .createRegion(7200, "razgrad");

            Region ruse = this.regionFactory
                    .createRegion(7000, "ruse");

            Region silistra = this.regionFactory
                    .createRegion(7500, "silistra");

            Region sliven = this.regionFactory
                    .createRegion(8800, "sliven");

            Region smolyan = this.regionFactory
                    .createRegion(4700, "smolyan");

            Region sofia = this.regionFactory
                    .createRegion(1000, "sofia");

            Region sofiaProvince = this.regionFactory
                    .createRegion(24, "sofiaProvince");

            Region staraZagora = this.regionFactory
                    .createRegion(6000, "staraZagora");

            Region targovishte = this.regionFactory
                    .createRegion(7700, "targovishte");

            Region haskovo = this.regionFactory
                    .createRegion(6300, "haskovo");

            Region shumen = this.regionFactory
                    .createRegion(9700, "shumen");

            Region yambol = this.regionFactory


                    .createRegion(8600, "yambol");



            List<Region> regions = List.of(global, blagoevgrad, burgas, varna,
                    velikoTarnovo, vidin, vratsa, gabrovo, dobrich, kardzhali,
                    kyustendil, lovech, montana, pazardzhik, pernik, pleven,
                    plovdiv, razgrad, ruse, silistra, sliven, smolyan, sofia, sofiaProvince,
                    staraZagora, targovishte, haskovo, shumen, yambol);

            regionsCatalog.setRegions(regions);

        }

    }

    public Optional<Region> getRegionById(int regionId) {
        return regionsCatalog.findRegionById(regionId);
    }

    public List<RegionDTO> getAllRegions() {
        return regionsCatalog.getRegions()
                .stream()
                .map(this::mapRegionToRegionDTO)
                .collect(Collectors.toList());
    }
    private RegionDTO mapRegionToRegionDTO(Region region){
        return RegionDTO.builder()
                .id(region.getId())
                .englishRegionName(region.getRegionName())
                .bulgarianRegionName(this.regionsCatalog.translateRegionNameToBulgarian(region.getRegionName()))
                .build();
    }
}