import React from 'react';
import { shallow } from "enzyme";
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


});
