import React from 'react';
import {shallow} from "enzyme";
import {Input, Button} from 'reactstrap';
import LoginPage from "./LoginPage"

describe('Login Page', () => {

    const defaultProps = {
        baseUrl: "/"
    };

    const USERNAME = 'alice';
    const PASSWORD = '1234';

    const INVALID_USERNAME = 'a';
    const INVALID_PASSWORD = '123';

    const createWrapper = props => shallow(<LoginPage {...defaultProps} {...props}/>);

    const component = createWrapper();

    it('snapshot testing', () => {

        expect(component).toMatchSnapshot();
    });

    describe('TextField validation', () => {

        it('should render Input with placeholder', () => {
            const textField = component.find(Input).at(0).prop('placeholder');

            expect(textField).toBe('Username');
        });

        it('should render valid field when username is valid', () => {
            const username = component.find(Input).at(0);

            username.props().onChange({target: {value: USERNAME}});
            const username1 = component.find(Input).at(0);

            expect(username1.prop('invalid')).toBeFalsy();
        });

        it('should render invalid field when username is not valid', () => {
            const username = component.find(Input).at(0);

            expect(username.prop('invalid')).toBeFalsy();
            username.props().onChange({target: {value: INVALID_USERNAME}});
            const username1 = component.find(Input).at(0);

            expect(username1.prop('invalid')).toBeTruthy();
        });

        it('should render valid field when password is valid', () => {
            const password = component.find(Input).at(1);

            password.props().onChange({target: {value: PASSWORD}});
            const password1 = component.find(Input).at(1);

            expect(password1.prop('invalid')).toBeFalsy();
        });

        it('should render invalid field when username is not valid', () => {
            const password = component.find(Input).at(1);

            expect(password.prop('invalid')).toBeFalsy();
            password.props().onChange({target: {value: INVALID_PASSWORD}});
            const password1 = component.find(Input).at(1);

            expect(password1.prop('invalid')).toBeTruthy();
        });

    });

    describe('Submit button validation', function () {

        it('should disable login button if input fields are not valid', () => {
            const username = component.find(Input).at(0);
            const password = component.find(Input).at(1);

            username.props().onChange({target: {value: INVALID_USERNAME}});
            password.props().onChange({target: {value: INVALID_PASSWORD}});

            const submit = component.find(Button);
            component.update();

            expect(submit.prop('disabled')).toBeTruthy();

        });

        it('should enable login button if input fields are valid', () => {
            const username = component.find(Input).at(0);
            const password = component.find(Input).at(1);
            const loginButton = component.find(Button);

            expect(loginButton.prop('disabled')).toBeTruthy();

            username.props().onChange({target: {value: USERNAME}});
            password.props().onChange({target: {value: PASSWORD}});
            component.update();

            const loginButton1 = component.find(Button);

            expect(loginButton1.prop('disabled')).toBeFalsy();

        });

    });

});
