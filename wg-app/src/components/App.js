import React from 'react';
import { Router, Route, Switch } from 'react-router-dom';
import history from '../history';

const App = () => {

    return (
        <Router history={history}>
            <div>
                <Switch>
                    <Route path="/" exact component/>
                </Switch>
            </div>
        </Router>
    )
};

export default App;