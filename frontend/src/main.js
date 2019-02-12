import React from 'react';
import ReactDOM from 'react-dom';

import Page from './Page';

ReactDOM.render(<Page {...window.pageProps} />, document.getElementById('page'));
