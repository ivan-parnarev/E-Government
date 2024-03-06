import { configureStore } from '@reduxjs/toolkit';

import campaignReducer from './campaigns/campaignsSlice';

const store = configureStore({
    reducer: {
        campaign: campaignReducer,
    },
});

export default store;
export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
