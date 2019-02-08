import React from 'react';
import {Input, Label, Col, Container, FormGroup, Form, Button} from 'reactstrap';

export default class LoginPage extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            email: false,
            password: false
        }
    }

    validate(fieldName, fieldValue) {
        this.setState({
            [fieldName]: fieldValue.length > 0
        })
    }

    canSubmit() {
        const {email, password} = this.state;
        return email && password;
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
                                    <Label>Email</Label>
                                    <Input type="email"
                                           name="username"
                                           className="login-form__textfield"
                                           placeholder="example@example.com"
                                           onChange={(e) => this.validate('email', e.target.value)}
                                    />
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
                                    />
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
