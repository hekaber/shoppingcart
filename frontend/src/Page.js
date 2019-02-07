import React from 'react';
import PropTypes from 'prop-types'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import LoginPage from './page/LoginPage'


const Page = props => {
    //
    // const makePath = relPath => createUrl(window.location.host, brand, market, language, relPath);
    //
    // const {baseUrl} = props;

    return (
        <Router>
            <Switch>
                <Route exact path={`/login`}>
                    <LoginPage/>
                </Route>
            </Switch>
        </Router>
    )
};

Page.propTypes = {
    baseUrl: PropTypes.string.isRequired
};

export default Page;
