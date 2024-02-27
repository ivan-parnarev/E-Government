import React from "react";
import { BarChart } from "@mui/x-charts/BarChart";
import styles from "./CensusResultsComponent.module.css";

function CensusResultsComponent({ results, selectedRegions }) {
  const filteredResults = results.filter((region) =>
    selectedRegions.includes(region.regionName)
  );

  if (filteredResults.length === 0) {
    return;
  }

  const aggregatePopulationData = (demographicType, category) => {
    if (filteredResults.length === 0) {
      return results.map((districtResult) => {
        const demographic = districtResult[demographicType];
        return demographic ? demographic[category] || 0 : 0;
      });
    }

    return filteredResults.map((districtResult) => {
      const demographic = districtResult[demographicType];
      return demographic ? demographic[category] || 0 : 0;
    });
  };

  const extractDistrictNames = () => {
    return filteredResults.map((districtResult) => districtResult.regionName);
  };

  const genderCategories = ["мъже", "жени"];
  const genderData = genderCategories.map((category) =>
    aggregatePopulationData("gender", category)
  );

  const ageCategories = ["18-30", "31-50", "51+"];
  const ageData = ageCategories.map((category) =>
    aggregatePopulationData("age", category)
  );

  const nationalityCategories = ["българска", "ромска", "турска", "друга"];
  const nationalityData = nationalityCategories.map((category) =>
    aggregatePopulationData("nationality", category)
  );

  const educationCategories = ["начално", "средно", "висше"];
  const educationData = educationCategories.map((category) =>
    aggregatePopulationData("education", category)
  );

  const xLabels = extractDistrictNames();

  return (
    <div className={styles.regionResults}>
      <h3 className={styles.regionTitle}>
        <b>Общо население по пол</b>
      </h3>
      <BarChart
        width={500}
        height={300}
        series={genderCategories.map((category, index) => ({
          data: genderData[index],
          label: category,
          id: `genderId${index}`,
          stack: "total",
        }))}
        xAxis={[{ data: xLabels, scaleType: "band", categoryGapRatio: 0.5 }]}
      />

      <h3 className={styles.regionTitle}>
        <b>Общо население по възраст</b>
      </h3>
      <BarChart
        width={500}
        height={300}
        series={ageCategories.map((category, index) => ({
          data: ageData[index],
          label: category,
          id: `ageId${index}`,
          stack: "total",
        }))}
        xAxis={[{ data: xLabels, scaleType: "band", categoryGapRatio: 0.5 }]}
      />

      <h3 className={styles.regionTitle}>
        <b>Общо население по националност</b>
      </h3>
      <BarChart
        width={500}
        height={300}
        series={nationalityCategories.map((category, index) => ({
          data: nationalityData[index],
          label: category,
          id: `nationalityId${index}`,
          stack: "total",
        }))}
        xAxis={[{ data: xLabels, scaleType: "band", categoryGapRatio: 0.5 }]}
      />

      <h3 className={styles.regionTitle}>
        <b>Общо население по образование</b>
      </h3>
      <BarChart
        width={500}
        height={300}
        series={educationCategories.map((category, index) => ({
          data: educationData[index],
          label: category,
          id: `educationId${index}`,
          stack: "total",
        }))}
        xAxis={[{ data: xLabels, scaleType: "band", categoryGapRatio: 0.5 }]}
      />
    </div>
  );
}

export default CensusResultsComponent;
