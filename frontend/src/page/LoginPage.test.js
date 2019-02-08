import React from 'react';
import { shallow } from "enzyme";
import { Input } from 'reactstrap';
import LoginPage from "./LoginPage"

describe('Login Page', function () {

    const defaultProps = {
        baseUrl: "/"
    };

    const createWrapper = props => shallow(<LoginPage {...defaultProps} {...props}/>);

    it('snapshot testing', () => {
        const component = createWrapper();

        expect(component).toMatchSnapshot();
    });

    it('should render Input with placeholder', function () {
        const component = createWrapper();

        const textField = component.find(Input).at(0).prop('placeholder');

        expect(textField).toBe('example@example.com');

    });



});
