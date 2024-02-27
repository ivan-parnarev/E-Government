import React from "react";
import styles from "./VotingResultsComponent.module.css";
import { PieChart } from "@mui/x-charts";

function VotingResultsComponent({ results, selectedDistrict }) {
  return (
    <>
      {results.map((districtResult) => {
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
          <div key={districtResult.regionName} className={styles.regionResults}>
            <h3 className={styles.regionTitle}>
              <b>{districtResult.regionName}</b>
            </h3>
            <PieChart
              series={[
                {
                  data: districtResult.candidates.map((candidate, index) => ({
                    id: index,
                    value: candidate.candidateVotes,
                    label: `${candidate.candidateName} (${(
                      (candidate.candidateVotes / totalVotes) *
                      100
                    ).toFixed(2)}%)`,
                  })),
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
    </>
  );
}

export default VotingResultsComponent;
