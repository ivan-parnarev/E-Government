import { PayloadAction, createSlice } from '@reduxjs/toolkit';

import { CampaignsState } from './interfaces/CampaignsStateInterface';
import { RootState } from '../store';

const initialState: CampaignsState = {
    filteredCampaigns: [],
};

const campaignsSlice = createSlice({
    name: 'campaigns',
    initialState,
    reducers: {
        setCampaigns: (state, action: PayloadAction<[]>) => {
            state.filteredCampaigns = action.payload;
        },
    },
});

export const getCampaigns = (state: RootState) => state.campaign.filteredCampaigns;
export const { setCampaigns } = campaignsSlice.actions;
export default campaignsSlice.reducer;
