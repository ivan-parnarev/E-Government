package com.egovernment.authentication.service;

import com.egovernment.authentication.domain.dto.AddressDTO;
import com.egovernment.authentication.domain.entity.Address;
import com.egovernment.authentication.domain.entity.City;
import com.egovernment.authentication.domain.entity.Municipality;
import com.egovernment.authentication.domain.entity.Region;
import com.egovernment.authentication.domain.enums.Country;
import com.egovernment.authentication.repository.CityRepository;
import com.egovernment.authentication.repository.MunicipalityRepository;
import com.egovernment.authentication.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class LocationServiceTest {

    private static final String TEST_USER_REGION = "Софийска";
    private static final String TEST_USER_MUNICIPALITY = "Столична община";
    private static final String TEST_USER_CITY = "София";
    @Mock
    private RegionRepository regionRepository;
    @Mock
    private MunicipalityRepository municipalityRepository;
    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private LocationService locationService;

    private Region region;
    private Municipality municipality;
    private City city;

    @BeforeEach
    void setUp() {
        this.region = Region.builder()
                .name(TEST_USER_REGION)
                .build();
        this.municipality = Municipality.builder()
                .name(TEST_USER_MUNICIPALITY)
                .build();
        this.city = City.builder()
                .name(TEST_USER_CITY)
                .build();
    }

    @Test
    void testInitCities() throws IOException {
        ClassPathResource mockedResource = mock(ClassPathResource.class);

        locationService.initCities();

        verify(cityRepository, times(1)).saveAllAndFlush(anyList());
    }

    @Test
    void testInitRegions() throws IOException {
        ClassPathResource mockedResource = mock(ClassPathResource.class);

        locationService.initRegions();

        verify(regionRepository, times(1)).saveAllAndFlush(anyList());
    }

    @Test
    void testInitMunicipalities() throws IOException {
        ClassPathResource mockedResource = mock(ClassPathResource.class);

        locationService.initMunicipalities();

        verify(municipalityRepository, times(1)).saveAllAndFlush(anyList());
    }

    @Test
    void testReadFromFile() throws IOException {
        List<String> locations = new ArrayList<>();
        ClassPathResource mockedResource = mock(ClassPathResource.class);
        when(mockedResource.getInputStream()).thenReturn(new ByteArrayInputStream("Location1, Location2".getBytes()));

        locationService.readFromFile(locations, mockedResource);

        assertEquals(2, locations.size());
        assertTrue(locations.contains("Location1"));
        assertTrue(locations.contains("Location2"));
    }

    @Test
    void testMapAddressToDto() {
        Address address = Address.builder()
                .country(Country.България)
                .city(this.city)
                .municipality(this.municipality)
                .region(this.region)
                .build();

        AddressDTO addressDTO = locationService.mapAddressToDto(address);

        assertNotNull(addressDTO);
        assertEquals(TEST_USER_REGION, addressDTO.getRegion());
        assertEquals(TEST_USER_MUNICIPALITY, addressDTO.getMunicipality());
        assertEquals(TEST_USER_CITY, addressDTO.getCity());
    }
}
