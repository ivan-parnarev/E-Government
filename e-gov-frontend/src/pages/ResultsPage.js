import React from "react";
import { BarChart, PieChart } from "@mui/x-charts";
import styles from "./ResultsPage.module.css";

const campaign = {
  LOCAL: {
    campaignTitle: "Избори за кмет",
    date: "2024-01-18",
    votes: [
      {
        region: "Благоевград",
        votes: 1200,
      },
      {
        region: "Пловдив",
        votes: 1500,
      },
      {
        region: "София",
        votes: 2000,
      },
    ],
    results: [
      {
        regionName: "Благоевград",
        candidates: [
          {
            candidateName: "Candidate 1",
            candidateParty: "Party A",
            candidateVotes: 387,
          },
          {
            candidateName: "Candidate 2",
            candidateParty: "Party B",
            candidateVotes: 65,
          },
          {
            candidateName: "Candidate 3",
            candidateParty: "Party C",
            candidateVotes: 748,
          },
        ],
      },
      {
        regionName: "Пловдив",
        candidates: [
          {
            candidateName: "Candidate 1",
            candidateParty: "Party A",
            candidateVotes: 1050,
          },
          {
            candidateName: "Candidate 2",
            candidateParty: "Party B",
            candidateVotes: 200,
          },
          {
            candidateName: "Candidate 3",
            candidateParty: "Party C",
            candidateVotes: 250,
          },
        ],
      },
      {
        regionName: "София",
        candidates: [
          {
            candidateName: "Candidate 1",
            candidateParty: "Party A",
            candidateVotes: 823,
          },
          {
            candidateName: "Candidate 2",
            candidateParty: "Party B",
            candidateVotes: 920,
          },
          {
            candidateName: "Candidate 3",
            candidateParty: "Party C",
            candidateVotes: 257,
          },
        ],
      },
    ],
  },

  GLOBAL: {
    campaignTitle: "Избори за президент",
    date: "2024-01-18",
    votes: [
      {
        region: null,
        votes: 2700,
      },
    ],
    results: [
      {
        regionName: null,
        candidates: [
          {
            candidateName: "Candidate 1",
            candidateParty: "Party A",
            candidateVotes: 1800,
          },
          {
            candidateName: "Candidate 2",
            candidateParty: "Party B",
            candidateVotes: 900,
          },
        ],
      },
    ],
  },
};

export function ResultsPage() {
  const barChartCategories = campaign.LOCAL.results.map(
    (result) => result.regionName
  );
  const barChartData = campaign.LOCAL.results.map((result) => {
    const totalVotes = result.candidates.reduce(
      (total, candidate) => total + candidate.candidateVotes,
      0
    );
    return totalVotes;
  });

  return (
    <div className={styles.containerBackground}>
      <div className={styles.container}>
        <div className={styles.resultsContainer}>
          <h2>{campaign.LOCAL.campaignTitle}</h2>
          {campaign.LOCAL.results.map((districtResult) => (
            <div key={districtResult.regionName}>
              <h3>
                <b>{districtResult.regionName}</b>
              </h3>
              <BarChart
                xAxis={[
                  {
                    id: `barCategories_${districtResult.regionName}`,
                    data: districtResult.candidates.map(
                      (candidate) => candidate.candidateName
                    ),
                    scaleType: "band",
                  },
                ]}
                series={[
                  {
                    data: districtResult.candidates.map(
                      (candidate) => candidate.candidateVotes
                    ),
                    color: "dodgerblue",
                  },
                ]}
                width={500}
                height={300}
              />

              <PieChart
                series={[
                  {
                    data: districtResult.candidates.map((candidate, index) => ({
                      id: index,
                      value: candidate.candidateVotes,
                      label: candidate.candidateName,
                    })),
                    innerRadius: 90,
                    outerRadius: 130,
                    paddingAngle: 0,
                    cornerRadius: 0,
                    startAngle: -90,
                    endAngle: 90,
                    cx: 150,
                    cy: 150,
                  },
                ]}
                width={500}
                height={200}
              />
            </div>
          ))}

          <h2>{campaign.GLOBAL.campaignTitle}</h2>
          <div key="global">
            <BarChart
              xAxis={[
                {
                  id: "barCategories_global",
                  data: campaign.GLOBAL.results[0].candidates.map(
                    (candidate) => candidate.candidateName
                  ),
                  scaleType: "band",
                },
              ]}
              series={[
                {
                  data: campaign.GLOBAL.results[0].candidates.map(
                    (candidate) => candidate.candidateVotes
                  ),
                  color: "dodgerblue",
                },
              ]}
              width={500}
              height={300}
            />

            <PieChart
              series={[
                {
                  data: campaign.GLOBAL.results[0].candidates.map(
                    (candidate, index) => ({
                      id: index,
                      value: candidate.candidateVotes,
                      label: candidate.candidateName,
                    })
                  ),
                  innerRadius: 90,
                  outerRadius: 130,
                  paddingAngle: 0,
                  cornerRadius: 0,
                  startAngle: -90,
                  endAngle: 90,
                  cx: 150,
                  cy: 150,
                },
              ]}
              width={500}
              height={200}
            />
          </div>
        </div>
      </div>
    </div>
  );
}
