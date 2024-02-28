import axios from '../../../hooks/useAxiosInterceptor.js';
import API_URLS from '../../../utils/apiUtils.js';
import { formatDate, calculateDefaultEndDate } from '../../../utils/dateUtils.js';
import toast from 'react-hot-toast';
import Modal from 'react-bootstrap/Modal';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import InputGroup from 'react-bootstrap/InputGroup';
import FloatingLabel from 'react-bootstrap/FloatingLabel';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import styles from './CreateVotingCampaignComponent.module.css';
import { useEffect, useState } from 'react';
import useAuth from '../../../hooks/AuthContext.js';
import CampaignModalFooterComponent from '../../CampaignModalFooterComponent';
import { CreateVotingAddCandidateComponent } from './CreateVotingAddCandidateComponent.js';
import { CreateVotingReviewCandidatesComponent } from './CreateVotingReviewCandidatesComponent.js';

const TOTAL_STEPS = 3;

export function CreateVotingCampaignComponent({ show, onHide }) {
    const { userPin } = useAuth();
    const [currentStep, setCurrentStep] = useState(1);
    const [regions, setRegions] = useState([]);
    const [campaignData, setCampaignData] = useState({
        electionType: '',
        campaignTitle: '',
        campaignDescription: '',
        candidates: {},
        campaignStartDate: '',
        campaignEndDate: '',
    });

    const {
        electionType,
        campaignTitle,
        campaignDescription,
        candidates,
        campaignStartDate,
        campaignEndDate,
    } = campaignData;

    const fetchRegions = async () => {
        try {
            const response = await axios.get(API_URLS.GET_REGIONS);
            const filteredRegions = response.data.filter((region) => region.id !== 1);
            setRegions(filteredRegions);
        } catch (error) {
            console.log('Error fetching regions: ', error.message);
        }
    };

    useEffect(() => {
        fetchRegions();
    }, []);

    const handleElectionTypeChange = (e) => {
        setCampaignData({
            ...campaignData,
            electionType: e.target.value,
        });
    };

    const handleCampaignTitleChange = (e) => {
        setCampaignData({
            ...campaignData,
            campaignTitle: e.target.value,
        });
    };

    const handleCampaignDescriptionChange = (e) => {
        setCampaignData({
            ...campaignData,
            campaignDescription: e.target.value,
        });
    };

    const handleStartDateChange = (e) => {
        const startDate = e.target.value;

        setCampaignData((prevData) => {
            const updatedData = {
                ...prevData,
                campaignStartDate: startDate,
            };

            if (!updatedData.campaignEndDate) {
                const endDate = calculateDefaultEndDate(startDate);
                updatedData.campaignEndDate = formatDate(endDate);
            }

            return updatedData;
        });
    };

    const handleEndDateChange = (e) => {
        setCampaignData({
            ...campaignData,
            campaignEndDate: e.target.value,
        });
    };

    const handleFormSubmit = (event) => {
        event.preventDefault();

        let location = '';

        const currUserData = {
            creatorUserPin: userPin,
            campaignType: 'VOTING',
            campaignTitle: campaignData.campaignTitle,
            campaignDescription: campaignData.campaignDescription,
            campaignStartDate: campaignData.campaignStartDate,
            campaignEndDate: campaignData.campaignEndDate,
            campaignRegion:
                campaignData.electionType === 'MAYOR' || campaignData.electionType === 'COUNCIL'
                    ? 'LOCAL'
                    : 'GLOBAL',
            elections: Object.keys(campaignData.candidates)
                .filter((electionRegion) => electionRegion.trim() !== '')
                .map((electionRegion) => {
                    const candidates = campaignData.candidates[electionRegion]
                        .filter(
                            (candidate) =>
                                candidate.candidateName.trim() !== '' &&
                                candidate.candidateParty.trim() !== '' &&
                                candidate.candidateNumber.trim() !== ''
                        )
                        .map((candidate) => ({
                            candidateName: candidate.candidateName,
                            candidateParty: candidate.candidateParty,
                            candidateNumber: candidate.candidateNumber,
                        }));

                    if (candidates.length > 0) {
                        return {
                            electionType: campaignData.electionType,
                            electionRegion,
                            candidates,
                        };
                    }

                    return null;
                })
                .filter((election) => election !== null),
        };

        axios
            .post(API_URLS.CREATE_VOTE, currUserData, {
                headers: { 'Content-Type': 'application/json' },
            })
            .then((response) => {
                if (response.status === 201) {
                    location = response.headers.get('location');

                    return response.data;
                }
            })
            .then((data) => {
                if (data) {
                    const successMessage = `${data.message} `;
                    toast.success(successMessage);
                }

                window.location.href = location;
            })
            .catch((error) => console.error('Error:', error.message));
    };

    const handleContinue = () => {
        if (currentStep < TOTAL_STEPS) {
            if (validateStep(currentStep)) {
                setCurrentStep(currentStep + 1);
            }
        }
    };

    const validateStep = (step) => {
        return true;
    };

    const handleBack = () => {
        if (currentStep > 1) {
            setCurrentStep(currentStep - 1);
        }
    };

    return (
        <Modal
            show={show}
            onHide={onHide}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >
            <Modal.Header>
                <h2>Създаване на кампания за избори</h2>
            </Modal.Header>

            <Modal.Body className={styles.createVotingCampaignContainer}>
                {currentStep === 1 && (
                    <>
                        <FloatingLabel
                            label="Тип на изборната кампания:"
                            className={styles.createVotingCampaignInputGroup}
                        >
                            <Form.Select
                                aria-label="Floating label select example"
                                onChange={handleElectionTypeChange}
                                value={electionType}
                            >
                                <option></option>
                                <option value="MAYOR">Избори за кмет</option>
                                <option value="PRESIDENT">Президентски избори</option>
                                <option value="PARLIAMENT">Парламентарни избори</option>
                                <option value="COUNCIL">Избори за общински съвет</option>
                            </Form.Select>
                        </FloatingLabel>

                        <FloatingLabel label="Име на кампанията:" className="mb-3">
                            <Form.Control
                                type="text"
                                placeholder=""
                                onChange={handleCampaignTitleChange}
                                value={campaignTitle}
                            />
                        </FloatingLabel>

                        <FloatingLabel label="Описание на кампанията:" className="mb-3">
                            <Form.Control
                                type="text"
                                placeholder=""
                                onChange={handleCampaignDescriptionChange}
                                value={campaignDescription}
                            />
                        </FloatingLabel>

                        <InputGroup className={styles.createVotingCampaignCandidateInputDateGroup}>
                            <Row>
                                <p className={styles.createVotingCampaignInputGroupInputLabel}>
                                    Избери начало и край на кампанията:
                                </p>
                            </Row>
                            <Row>
                                <Col>
                                    <FloatingLabel label="Начална дата">
                                        <input
                                            type="datetime-local"
                                            className="form-control"
                                            value={formatDate(campaignStartDate)}
                                            onChange={handleStartDateChange}
                                        />
                                    </FloatingLabel>
                                </Col>

                                <Col>
                                    <FloatingLabel label="Крайна дата">
                                        <input
                                            type="datetime-local"
                                            className="form-control"
                                            value={formatDate(campaignEndDate)}
                                            onChange={handleEndDateChange}
                                        />
                                    </FloatingLabel>
                                </Col>
                            </Row>
                        </InputGroup>
                    </>
                )}

                {currentStep === 2 && (
                    <CreateVotingAddCandidateComponent
                        electionType={electionType}
                        candidates={candidates}
                        regions={regions}
                        setCandidates={(updatedCandidates) =>
                            setCampaignData({
                                ...campaignData,
                                candidates: updatedCandidates,
                            })
                        }
                    />
                )}

                {currentStep === 3 && (
                    <CreateVotingReviewCandidatesComponent
                        campaignData={campaignData}
                        electionType={electionType}
                        regions={regions}
                    />
                )}

                <div className={styles.censusModalButtonContainer}>
                    <Button
                        className={styles.censusModalButton}
                        disabled={currentStep === 1}
                        onClick={handleBack}
                    >
                        ←
                    </Button>

                    <Button
                        className={styles.censusModalButton}
                        disabled={currentStep === 3}
                        onClick={handleContinue}
                    >
                        →
                    </Button>
                </div>
            </Modal.Body>

            <Modal.Footer>
                <CampaignModalFooterComponent
                    submitButtonDisabled="false"
                    buttonText="Създай"
                    onSubmit={handleFormSubmit}
                    onHide={onHide}
                />
            </Modal.Footer>
        </Modal>
    );
}
