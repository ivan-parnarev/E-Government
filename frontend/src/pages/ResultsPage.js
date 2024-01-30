import React, { useState } from "react";
import { PieChart } from "@mui/x-charts";
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
  const [selectedDistrict, setSelectedDistrict] = useState(null);

  const totalGlobalVotes = campaign.GLOBAL.results[0].candidates.reduce(
    (total, candidate) => total + candidate.candidateVotes,
    0
  );

  return (
    <div className={styles.containerBackground}>
      <div className={styles.container}>
        <div className={styles.resultsContainer}>
          <div className={styles.results}>
            <h2>{campaign.GLOBAL.campaignTitle}</h2>
            <div key="global">
              <PieChart
                series={[
                  {
                    data: campaign.GLOBAL.results[0].candidates.map(
                      (candidate, index) => ({
                        id: index,
                        value: candidate.candidateVotes,
                        label: `${candidate.candidateName} (${(
                          (candidate.candidateVotes / totalGlobalVotes) *
                          100
                        ).toFixed(2)}%)`,
                      })
                    ),
                    highlightScope: { faded: "global", highlighted: "item" },
                    faded: {
                      innerRadius: 90,
                      additionalRadius: 0,
                      color: "gray",
                    },
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
                width={520}
                height={200}
              />
            </div>
          </div>

          <div className={styles.results}>
            <h2>{campaign.LOCAL.campaignTitle}</h2>
            <select
              className={styles.regionSelect}
              value={selectedDistrict || ""}
              onChange={(e) => setSelectedDistrict(e.target.value || null)}
            >
              <option value="">Избери регион</option>
              {campaign.LOCAL.results.map((districtResult) => (
                <option
                  key={districtResult.regionName}
                  value={districtResult.regionName}
                >
                  {districtResult.regionName}
                </option>
              ))}
            </select>

            {campaign.LOCAL.results.map((districtResult) => {
              if (
                selectedDistrict &&
                districtResult.regionName !== selectedDistrict
              ) {
                return null;
              }

              const totalVotes = districtResult.candidates.reduce(
                (total, candidate) => total + candidate.candidateVotes,
                0
              );

              return (
                <div
                  key={districtResult.regionName}
                  className={styles.regionResults}
                >
                  <h3 className={styles.regionTitle}>
                    <b>{districtResult.regionName}</b>
                  </h3>
                  <PieChart
                    series={[
                      {
                        data: districtResult.candidates.map(
                          (candidate, index) => ({
                            id: index,
                            value: candidate.candidateVotes,
                            label: `${candidate.candidateName} (${(
                              (candidate.candidateVotes / totalVotes) *
                              100
                            ).toFixed(2)}%)`,
                          })
                        ),
                        highlightScope: {
                          faded: "global",
                          highlighted: "item",
                        },
                        faded: {
                          innerRadius: 90,
                          additionalRadius: 0,
                          color: "gray",
                        },
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
                    width={520}
                    height={200}
                  />
                </div>
              );
            })}
          </div>
        </div>
      </div>
    </div>
  );
}
