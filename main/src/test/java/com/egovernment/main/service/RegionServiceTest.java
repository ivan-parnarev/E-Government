package com.egovernment.main.service;

import com.egovernment.main.domain.dto.region.RegionDTO;
import com.egovernment.main.domain.entity.Region;
import com.egovernment.main.domain.entity.RegionsCatalog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegionServiceTest {
    @Mock
    private RegionsCatalog regionsCatalog;
    @InjectMocks
    private RegionService regionService;
    private final String TEST_REGION_NAME = "TestRegion";
    private final String TEST_BULGARIAN_REGION_NAME = "ТестРегион";
    private final int TEST_POSTAL_CODE = 1000;
    private final Region TEST_REGION = Region.builder()
            .regionName(TEST_REGION_NAME)
            .id(TEST_POSTAL_CODE)
            .build();
    @Test
    public void testInitRegions(){
        when(this.regionsCatalog.getRegions()).thenReturn(Collections.emptyList());
        regionService.initRegions();
        verify(this.regionsCatalog, times(1)).setRegions(anyList());
    }

    @Test
    public void tesGetRegionById(){
        when(regionsCatalog.findRegionById(TEST_POSTAL_CODE)).thenReturn(Optional.of(TEST_REGION));
        Optional<Region> result = this.regionService.getRegionById(TEST_POSTAL_CODE);
        assertTrue(result.isPresent());
        assertEquals(TEST_REGION.getRegionName(), result.get().getRegionName());
        assertEquals(TEST_REGION.getId(), result.get().getId());
    }

    @Test
    public void testGetAllRegions(){
        when(this.regionsCatalog.getRegions()).thenReturn(List.of(TEST_REGION));
        when(this.regionsCatalog.translateRegionNameToBulgarian(TEST_REGION_NAME))
                .thenReturn(TEST_BULGARIAN_REGION_NAME);

        List<RegionDTO> result = this.regionService.getAllRegions();

        assertEquals(1, result.size());
        assertEquals(TEST_REGION_NAME, result.get(0).getEnglishRegionName());
        assertEquals(TEST_BULGARIAN_REGION_NAME, result.get(0).getBulgarianRegionName());
    }

}
