package com.egovernment.main.service;

import com.egovernment.main.client.AccessControlClient;
import com.egovernment.main.domain.dto.common.CampaignFilteredDTO;
import com.egovernment.main.hashing.HashUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CacheService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisTemplate<String, String> checksumRedisTemplate;
    private final AccessControlClient accessControlClient;

    public List<CampaignFilteredDTO> getCachedCampaigns(String regionName) {

        String checksumKey = getChecksumKey(regionName);
        String dataKey = getDataKey(regionName);

        List<CampaignFilteredDTO> cachedCampaigns = (List<CampaignFilteredDTO>) redisTemplate.opsForValue().get(dataKey);
        String cachedChecksum = checksumRedisTemplate.opsForValue().get(checksumKey);

        List<CampaignFilteredDTO> newCampaigns = accessControlClient.getActiveCampaigns(regionName).getBody();
        String newChecksum = computeChecksum(newCampaigns);

        if (newChecksum.equals(cachedChecksum)) {
            return cachedCampaigns != null ? cachedCampaigns : newCampaigns;
        } else {
            redisTemplate.opsForValue().set(dataKey, newCampaigns);
            checksumRedisTemplate.opsForValue().set(checksumKey, newChecksum);
            return newCampaigns;
        }

    }

    private String computeChecksum(List<CampaignFilteredDTO> campaigns) {
        String serializedCampaigns = serializeCampaigns(campaigns);
        return HashUtil.hashText(serializedCampaigns);
    }

    private String serializeCampaigns(List<CampaignFilteredDTO> campaigns) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(campaigns);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing campaigns", e);
        }
    }

    private String getChecksumKey(String regionName) {
        return "checksum:" + regionName;
    }

    private String getDataKey(String regionName) {
        return "data:" + regionName;
    }

}