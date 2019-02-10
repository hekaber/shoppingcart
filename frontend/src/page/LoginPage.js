import React from 'react';
import {Input, Label, Col, Container, FormGroup, Form, Button, FormFeedback} from 'reactstrap';

export default class LoginPage extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: ''
        }
    }

    validate(fieldName, fieldValue) {
        const usernameRex = /^[a-zA-Z0-9]{3,10}$/;
        const passwordRex = /^[a-zA-Z0-9!@#$%^&*]{4,8}$/;
        const regex = fieldName === 'username' ? usernameRex : passwordRex;
        fieldValue.length > 0 && this.handleFieldChange(fieldName, fieldValue, regex);
    }

    handleFieldChange(fieldName, fieldValue, regex) {
        this.setState({
            [fieldName]: regex.test(fieldValue)
        })
    }

    canSubmit() {
        const {username, password} = this.state;
        return username && password;
    }

    render() {
        return (
            <div className="login-form">
                <Container>
                    <Form>
                        <Col>
                            <FormGroup>
                                <h2>Login Form</h2>
                                <div className="form-group">
                                    <Label>Username</Label>
                                    <Input type="username"
                                           name="username"
                                           className="login-form__textfield"
                                           placeholder="Username"
                                           onChange={(e) => this.validate('username', e.target.value)}
                                           invalid={!this.state.username && this.state.username !== ''}
                                    />
                                    <FormFeedback>Snap! your username is not valid</FormFeedback>
                                    <small id="emailHelp" className="form-text text-muted">We'll never share your email
                                        with anyone else.
                                    </small>
                                </div>
                            </FormGroup>
                        </Col>
                        <Col>
                            <FormGroup>
                                <div className="form-group">
                                    <Label>Password</Label>
                                    <Input type="password"
                                           name="password"
                                           className="login-form__textfield"
                                           placeholder="*******"
                                           onChange={(e) => this.validate('password', e.target.value)}
                                           invalid={!this.state.password && this.state.password !== ''}
                                    />
                                    <FormFeedback>Password must be more than 4 characters & less than 8
                                        characters</FormFeedback>
                                </div>
                            </FormGroup>
                        </Col>
                        <Button type="submit" disabled={!this.canSubmit()} className="btn btn-primary">Submit</Button>
                    </Form>
                </Container>
            </div>
        )
    }
}
