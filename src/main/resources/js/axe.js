async function axeData(params) {
    const obj = JSON.parse(params);
    await injectAxeScript();
    var results = await runAxe()
    results.id = 'id_' + (Date.now().toString(36) + Math.random().toString(36).substr(2, 5));
    results.title = obj.pageTitle == null ? document.title : obj.pageTitle;
    results.dimension = window.innerWidth + ' X ' + window.innerHeight;
    results.device = device();
    results.browser = getBrowser();
    results.date = getFormattedDate();
    return results;
}

function getBrowser() {
    const userAgent = navigator.userAgent;
    var browser = "Unkown";
    // Detect browser name
    browser = (/ucbrowser/i).test(userAgent) ? 'UCBrowser' : browser;
    browser = (/edg/i).test(userAgent) ? 'Edge' : browser;
    browser = (/googlebot/i).test(userAgent) ? 'GoogleBot' : browser;
    browser = (/chromium/i).test(userAgent) ? 'Chromium' : browser;
    browser = (/firefox|fxios/i).test(userAgent) && !(/seamonkey/i).test(userAgent) ? 'Firefox' : browser;
    browser = (/; msie|trident/i).test(userAgent) && !(/ucbrowser/i).test(userAgent) ? 'IE' : browser;
    browser = (/chrome|crios/i).test(userAgent) && !(/opr|opera|chromium|edg|ucbrowser|googlebot/i).test(userAgent) ? 'Chrome' : browser;
    browser = (/safari/i).test(userAgent) && !(/chromium|edg|ucbrowser|chrome|crios|opr|opera|fxios|firefox/i).test(userAgent) ? 'Safari' : browser;
    browser = (/opr|opera/i).test(userAgent) ? 'Opera' : browser;

    // detect browser version
    switch (browser) {
        case 'UCBrowser':
            return browser + ' ' + browserVersion(userAgent, /(ucbrowser)\/([\d\.]+)/i);
        case 'Edge':
            return browser + ' ' + browserVersion(userAgent, /(edge|edga|edgios|edg)\/([\d\.]+)/i);
        case 'GoogleBot':
            return browser + ' ' + browserVersion(userAgent, /(googlebot)\/([\d\.]+)/i);
        case 'Chromium':
            return browser + ' ' + browserVersion(userAgent, /(chromium)\/([\d\.]+)/i);
        case 'Firefox':
            return browser + ' ' + browserVersion(userAgent, /(firefox|fxios)\/([\d\.]+)/i);
        case 'Chrome':
            return browser + ' ' + browserVersion(userAgent, /(chrome|crios)\/([\d\.]+)/i);
        case 'Safari':
            return browser + ' ' + browserVersion(userAgent, /(safari)\/([\d\.]+)/i);
        case 'Opera':
            return browser + ' ' + browserVersion(userAgent, /(opera|opr)\/([\d\.]+)/i);
        case 'IE':
            const version = browserVersion(userAgent, /(trident)\/([\d\.]+)/i);
            // IE version is mapped using trident version
            // IE/8.0 = Trident/4.0, IE/9.0 = Trident/5.0
            return version ? browser + ' ' + parseFloat(version) + 4.0 : browser + ' ' + 7.0;
        default:
            return 'Unknown 0.0.0.0';
    }
}

function browserVersion(userAgent, regex) {
    return userAgent.match(regex) ? userAgent.match(regex)[2] : null;
}

function device() {
    var width = window.innerWidth;
    if (width < 768) {
        return 'Phone';
    } else if (width < 992) {
        return 'Tablet';
    } else if (width < 1200) {
        return 'Small Laptop';
    } else {
        return 'Laptop/Desktop';
    }
}

function getFormattedDate() {
    var date = new Date();
    var formattedDate = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
    return formattedDate;
}

function runAxe() {
    return new Promise(function (resolve, reject) {
        axe.run(document, {
            runOnly: {type: 'tag', values: ['wcag2a', 'wcag2aa', 'section508']},
            resultTypes: ['violations', 'incomplete', 'inapplicable'],
            rules: { 'accesskeys': { enabled: false } },
            reporter: 'v2'
        }).then(results => resolve(results))
    });
}

function injectAxeScript() {
    return new Promise((resolve, reject) => {
        const script = document.createElement('script');
        script.src = "https://cdn.jsdelivr.net/npm/axe-core@latest/axe.min.js";
        script.addEventListener('load', resolve);
        script.addEventListener('error', e => reject(e.error));
        document.head.appendChild(script);
    });
}