import React from 'react';
import {Input} from 'reactstrap';

export default class LoginPage extends React.Component {

    render() {
        return (
            <div className="login-container">
                <Input type="username"
                       name="username"
                       placeholder="example@example.com hello    s"/>
            </div>
        )
    }
}
