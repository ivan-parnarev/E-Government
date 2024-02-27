import React, { useState } from "react";
import styles from "./ResultsPage.module.css";
import VotingResultsComponent from "../components/results/VotingResultsComponent";
import Button from "react-bootstrap/esm/Button";
import CensusResultsComponent from "../components/results/CensusResultsComponent";

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

const censusData = {
  LOCAL: {
    campaignTitle: "Census 2024",
    date: "2024-01-18",
    regions: [
      {
        regionName: "Видин",
        totalPopulation: 3000,
        gender: {
          мъже: 1300,
          жени: 1700,
        },
        age: {
          "18-30": 1000,
          "31-50": 700,
          "51+": 1300,
        },
        education: {
          начално: 200,
          средно: 2000,
          висше: 800,
        },
        nationality: {
          българска: 2000,
          ромска: 500,
          турска: 150,
          друга: 350,
        },
      },
      {
        regionName: "Варна",
        totalPopulation: 4500,
        gender: {
          мъже: 2100,
          жени: 2400,
        },
        age: {
          "18-30": 1500,
          "31-50": 1200,
          "51+": 1800,
        },
        education: {
          начално: 300,
          средно: 2800,
          висше: 1400,
        },
        nationality: {
          българска: 3500,
          ромска: 800,
          турска: 200,
          друга: 500,
        },
      },
      {
        regionName: "Бургас",
        totalPopulation: 2500,
        gender: {
          мъже: 1200,
          жени: 1300,
        },
        age: {
          "18-30": 800,
          "31-50": 600,
          "51+": 1100,
        },
        education: {
          начално: 150,
          средно: 1800,
          висше: 550,
        },
        nationality: {
          българска: 2000,
          ромска: 300,
          турска: 100,
          друга: 100,
        },
      },
      {
        regionName: "Монтана",
        totalPopulation: 3000,
        gender: {
          мъже: 1300,
          жени: 1700,
        },
        age: {
          "18-30": 1000,
          "31-50": 700,
          "51+": 1300,
        },
        education: {
          начално: 200,
          средно: 2000,
          висше: 800,
        },
        nationality: {
          българска: 2000,
          ромска: 500,
          турска: 150,
          друга: 350,
        },
      },
      {
        regionName: "Враца",
        totalPopulation: 4500,
        gender: {
          мъже: 2100,
          жени: 2400,
        },
        age: {
          "18-30": 1500,
          "31-50": 1200,
          "51+": 1800,
        },
        education: {
          начално: 300,
          средно: 2800,
          висше: 1400,
        },
        nationality: {
          българска: 3500,
          ромска: 800,
          турска: 200,
          друга: 500,
        },
      },
      {
        regionName: "Смолян",
        totalPopulation: 2500,
        gender: {
          мъже: 1200,
          жени: 1300,
        },
        age: {
          "18-30": 800,
          "31-50": 600,
          "51+": 1100,
        },
        education: {
          начално: 150,
          средно: 1800,
          висше: 550,
        },
        nationality: {
          българска: 2000,
          ромска: 300,
          турска: 100,
          друга: 100,
        },
      },
      {
        regionName: "Благоевград",
        totalPopulation: 3000,
        gender: {
          мъже: 1300,
          жени: 1700,
        },
        age: {
          "18-30": 1000,
          "31-50": 700,
          "51+": 1300,
        },
        education: {
          начално: 200,
          средно: 2000,
          висше: 800,
        },
        nationality: {
          българска: 2000,
          ромска: 500,
          турска: 150,
          друга: 350,
        },
      },
      {
        regionName: "София",
        totalPopulation: 4500,
        gender: {
          мъже: 2100,
          жени: 2400,
        },
        age: {
          "18-30": 1500,
          "31-50": 1200,
          "51+": 1800,
        },
        education: {
          начално: 300,
          средно: 2800,
          висше: 1400,
        },
        nationality: {
          българска: 3500,
          ромска: 800,
          турска: 200,
          друга: 500,
        },
      },
      {
        regionName: "Пловдив",
        totalPopulation: 2500,
        gender: {
          мъже: 1200,
          жени: 1300,
        },
        age: {
          "18-30": 800,
          "31-50": 600,
          "51+": 1100,
        },
        education: {
          начално: 150,
          средно: 1800,
          висше: 550,
        },
        nationality: {
          българска: 2000,
          ромска: 300,
          турска: 100,
          друга: 100,
        },
      },
    ],
  },
};

export function ResultsPage() {
  const [selectedDistrict, setSelectedDistrict] = useState(null);
  const [selectedRegions, setSelectedRegions] = useState([]);
  const [showVotingResults, setShowVotingResults] = useState(false);
  const [showCensusResults, setShowCensusResults] = useState(false);
  const [dropdownVisible, setDropdownVisible] = useState(false);

  const handleRegionChange = (region) => {
    if (selectedRegions.includes(region)) {
      setSelectedRegions(selectedRegions.filter((r) => r !== region));
    } else {
      setSelectedRegions([...selectedRegions, region]);
    }
  };
  const handleToggleVotingResults = () => {
    setShowVotingResults(true);
    setShowCensusResults(false);
  };

  const handleToggleCensusResults = () => {
    setShowVotingResults(false);
    setShowCensusResults(true);
  };

  const handleDropdownToggle = () => {
    setDropdownVisible(!dropdownVisible);
  };

  return (
    <div className={styles.container}>
      <div className={styles.buttonsGroup}>
        <Button
          variant="outline-light"
          size="lg"
          className={`${styles.activeButton} ${
            showVotingResults ? styles.active : ""
          }`}
          onClick={handleToggleVotingResults}
        >
          Гласуване
        </Button>
        <Button
          variant="outline-light"
          size="lg"
          className={`${styles.activeButton} ${
            showCensusResults ? styles.active : ""
          }`}
          onClick={handleToggleCensusResults}
        >
          Преброяване
        </Button>
      </div>

      <div className={styles.resultsContainer}>
        <div
          className={
            showVotingResults || showCensusResults ? styles.results : ""
          }
        >
          {showCensusResults && (
            <div className={styles.censusResultsContainer}>
              <div className={styles.dropdown}>
                <Button
                  variant="outline-light"
                  className={styles.dropbtn}
                  onClick={handleDropdownToggle}
                >
                  Избери регион
                </Button>
                <div
                  className={
                    dropdownVisible
                      ? styles.dropdownContentVisible
                      : styles.dropdownContent
                  }
                >
                  {censusData.LOCAL.regions.map((region) => (
                    <div
                      key={region.regionName}
                      className={styles.dropdownRegion}
                    >
                      <input
                        type="checkbox"
                        id={region.regionName}
                        checked={selectedRegions.includes(region.regionName)}
                        onChange={() => handleRegionChange(region.regionName)}
                      />
                      <label htmlFor={region.regionName}>
                        {region.regionName}
                      </label>
                    </div>
                  ))}
                </div>
              </div>
              <CensusResultsComponent
                results={censusData.LOCAL.regions}
                selectedRegions={selectedRegions}
              />
            </div>
          )}

          {showVotingResults && (
            <div className={styles.votingResultsContainer}>
              <h2>{campaign.GLOBAL.campaignTitle}</h2>
              <VotingResultsComponent results={campaign.GLOBAL.results} />
              <h2>{campaign.LOCAL.campaignTitle}</h2>
              <select
                className={styles.dropbtn}
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
              <VotingResultsComponent
                results={campaign.LOCAL.results}
                selectedDistrict={selectedDistrict}
              />
            </div>
          )}
        </div>
      </div>
    </div>
  );
}
