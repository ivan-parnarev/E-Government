package com.egovernment.egovauth.service;

import com.egovernment.egovauth.domain.entity.City;
import com.egovernment.egovauth.domain.entity.Municipality;
import com.egovernment.egovauth.domain.entity.Region;
import com.egovernment.egovauth.repository.CityRepository;
import com.egovernment.egovauth.repository.MunicipalityRepository;
import com.egovernment.egovauth.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class LocationServiceTest {

    public static final String TEST_USER_REGION = "Софийска";
    public static final String TEST_USER_MUNICIPALITY = "Столична община";
    public static final String TEST_USER_CITY = "София";
    @Mock
    private final RegionRepository regionRepository;
    @Mock
    private final MunicipalityRepository municipalityRepository;
    @Mock
    private final CityRepository cityRepository;

    @InjectMocks
    private LocationService locationService;

    private Region region;

    @BeforeEach
    void setUp() {
        this.region = Region.builder()
                .name(TEST_USER_REGION)
                .build();
        Municipality municipality = Municipality.builder()
                .name(TEST_USER_MUNICIPALITY)
                .build();
        City city = City.builder()
                .name(TEST_USER_CITY)
                .build();
    }
}
