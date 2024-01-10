package com.egovernment.egovauth.service;

import com.egovernment.egovauth.domain.dto.AddressDTO;
import com.egovernment.egovauth.domain.entity.Address;
import com.egovernment.egovauth.domain.entity.City;
import com.egovernment.egovauth.domain.entity.Municipality;
import com.egovernment.egovauth.domain.entity.Region;
import com.egovernment.egovauth.repository.CityRepository;
import com.egovernment.egovauth.repository.MunicipalityRepository;
import com.egovernment.egovauth.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final RegionRepository regionRepository;

    private final MunicipalityRepository municipalityRepository;

    private final CityRepository cityRepository;

    public void initRegions() throws IOException {
        List<String> regions = new ArrayList<>();
        List<Region> regionEntityList = new ArrayList<>();

        ClassPathResource resource = new ClassPathResource("Locations/Regions");
        readFromFile(regions, resource);

        for (String region : regions) {
            String[] regionWithPostCode = region.split(" ");
            String regionName = regionWithPostCode[0];
            String postcode = regionWithPostCode[1];
            Region newRegion = Region.builder().name(regionName).postcode(postcode).build();
            regionEntityList.add(newRegion);
        }

        regionRepository.saveAllAndFlush(regionEntityList);
    }

    public void initMunicipalities() throws IOException {
        List<String> municipalities = new ArrayList<>();
        List<Municipality> municipalitiesEntityList = new ArrayList<>();

        ClassPathResource resource = new ClassPathResource("Locations/Municipalities");
        readFromFile(municipalities, resource);

        for (String municipality : municipalities) {
            Municipality newMunicipality = Municipality.builder().name(municipality).build();
            municipalitiesEntityList.add(newMunicipality);
        }

        municipalityRepository.saveAllAndFlush(municipalitiesEntityList);
    }

    public void initCities() throws IOException {
        List<String> cities = new ArrayList<>();
        List<City> cityEntityList = new ArrayList<>();

        ClassPathResource resource = new ClassPathResource("Locations/Cities");
        readFromFile(cities, resource);

        for (String municipality : cities) {
            City newCity = City.builder().name(municipality).build();
            cityEntityList.add(newCity);
        }

        cityRepository.saveAllAndFlush(cityEntityList);
    }

    void readFromFile(List<String> locations, ClassPathResource resource) throws IOException {
        InputStream inputStream = resource.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] locationsNames = line.split(",\\s*");
            locations.addAll(Arrays.asList(locationsNames));
        }

        reader.close();
    }



    public AddressDTO mapAddressToDto(Address address) {

        String cityName = address.getCity() != null ? address.getCity().getName() : null;
        String villageName = address.getVillage() != null ? address.getVillage().getName() : null;

        return AddressDTO.builder()
                .country(address.getCountry().name())
                .region(address.getRegion().getName())
                .postcode(address.getRegion().getPostcode())
                .municipality(address.getMunicipality().getName())
                .city(cityName)
                .village(villageName)
                .build();
    }
}
