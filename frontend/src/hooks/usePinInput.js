import { useState } from 'react';

function usePinInput() {
    const [pinValue, setPinValue] = useState('');
    const [isValidPinValue, setIsValidPinValue] = useState(false);

    const validatePinValue = (input) => {
        const regex = /^[0-9]+$/;
        return regex.test(input);
    };

    const handlePinChange = (event) => {
        const newPinValue = event.target.value;
        setPinValue(newPinValue);
        setIsValidPinValue(validatePinValue(newPinValue));
    };

    return { pinValue, isValidPinValue, handlePinChange };
}

export default usePinInput;
