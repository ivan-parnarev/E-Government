export interface CommonCampaignProps {
  campaignType: string;
  campaignTitle: string;
  campaignDescription: string;
}

export interface CampaignProps extends CommonCampaignProps {
  electionId: string;
  campaignId: string;
}
