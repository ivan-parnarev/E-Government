import useAuth from '../hooks/AuthContext';
import UserAuthenticationComponent from '../components/user/UserAuthenticationComponent';
import UserProfileComponent from '../components/user/UserProfileComponent';

export function ProfilePage() {
    const { userPin } = useAuth();

    return (
        <div>
            <div>
                {userPin ? (
                    <UserProfileComponent />
                ) : (
                    <div>
                        <UserAuthenticationComponent />
                    </div>
                )}
            </div>
        </div>
    );
}
