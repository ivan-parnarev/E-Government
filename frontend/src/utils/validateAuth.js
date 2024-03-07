const validateSamePassword = (password1, password2) => {
    return password1 === password2;
};

const validatePasswordLength = (password) => {
    return password.length >= 8;
};

const validatePasswordStrength = (password) => {
    const lowerCaseRegex = /[a-z]/;
    const upperCaseRegex = /[A-Z]/;
    const numberRegex = /[0-9]/;

    const hasLowerCase = lowerCaseRegex.test(password);
    const hasUpperCase = upperCaseRegex.test(password);
    const hasNumber = numberRegex.test(password);

    return hasLowerCase && hasUpperCase && hasNumber;
};

const validatePassword = (password1, password2) => {
    const msg = {
        errors: [],
        success: false,
    };

    if (!validatePasswordLength(password1)) {
        msg.errors.push('Паролата трябва да е поне 8 знака');
    }

    if (!validatePasswordStrength(password1)) {
        msg.errors.push(
            'Паролата трябва да съдържа поне една голяма, една малка буква и една цифра'
        );
    }

    if (!validateSamePassword(password1, password2)) {
        msg.errors.push('Паролите не съвпадат');
    }

    if (Object.keys(msg).length === 0) {
        msg.success = true;
    }

    return msg;
};

export default validatePassword;
