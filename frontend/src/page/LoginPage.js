import React from 'react';
import { Input, Label, Col, Container, FormGroup, Form, Button, FormFeedback } from 'reactstrap';

export default class LoginPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            userName: null,
            password: null,
        };
    }

    validate(fieldName, fieldValue) {
        const usernameRex = /^[a-zA-Z0-9]{3,10}$/;
        const passwordRex = /^[a-zA-Z0-9!@#$%^&*]{4,8}$/;
        const regex = fieldName === 'userName' ? usernameRex : passwordRex;
        return fieldValue.length > 0 && this.handleFieldChange(fieldName, fieldValue, regex);
    }

    handleFieldChange(fieldName, fieldValue, regex) {
        let result = regex.test(fieldValue)? fieldValue : '';
        this.setState({
            [fieldName]: result,
        });
    }

    clickSubmit(){

        const { userName,password } = this.state;

        fetch(`/login`, {
            method: 'POST',
            headers:{
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({userName: userName,password: password})
        }).then(res => (res.ok ? location.href='/products' : Promise.reject(res.status)
        ))
    }

    canSubmit() {
        const { userName, password } = this.state;
        return userName && password;
    }

    handleOnSubmit(e) {
        e.preventDefault();
    }

    render() {
        const { userName, password } = this.state;
        return (
            <div className="login-form">
                <Container>
                    <Form onSubmit={(e) => this.handleOnSubmit(e)} >
                        <Col>
                            <FormGroup>
                                <h2>Login Form</h2>
                                <div className="form-group">
                                    <Label>Username</Label>
                                    <Input
                                        type="username"
                                        name="userName"
                                        className="login-form__textfield"
                                        placeholder="Username"
                                        onChange={e => this.validate('userName', e.target.value)}
                                        invalid={!userName && userName !== null}
                                    />
                                    <FormFeedback>Snap! your username is not valid</FormFeedback>
                                    <small id="emailHelp" className="form-text text-muted">
                                        { 'We\'ll never share your email with anyone else.'}
                                    </small>
                                </div>
                            </FormGroup>
                        </Col>
                        <Col>
                            <FormGroup>
                                <div className="form-group">
                                    <Label>Password</Label>
                                    <Input
                                        type="password"
                                        name="password"
                                        className="login-form__textfield"
                                        placeholder="*******"
                                        onChange={e => this.validate('password', e.target.value)}
                                        invalid={!password && password !== null}
                                    />
                                    <FormFeedback>
Password must be more than 4 characters & less than 8
                                        characters
                                    </FormFeedback>
                                </div>
                            </FormGroup>
                        </Col>
                        <Button type="submit" disabled={!this.canSubmit()} className="btn btn-primary" onClick={(e) => this.clickSubmit(e)}>Submit</Button>
                    </Form>
                </Container>
            </div>
        );
    }
}
