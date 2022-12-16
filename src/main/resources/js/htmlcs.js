async function getData(params) {
    const obj = JSON.parse(params);
    const codes = obj.ignoreCodes;

    await injectScript();
    const results = await runHtmlCS(obj.standard, codes);
    const pageTitle = obj.pageTitle == null ? document.title : obj.pageTitle;
    return {
        errors: resultsCount(results, 1),
        warnings: resultsCount(results, 2),
        notices: resultsCount(results, 3),
        standard: obj.standard,
        date: getFormattedDate(),
        dimension: window.innerWidth + ' X ' + window.innerHeight,
        url: window.location.href,
        title: pageTitle,
        device: device(),
        browser: getBrowser(),
        results: results,
        id: 'id_' + (Date.now().toString(36) + Math.random().toString(36).substr(2, 5))
    };
}

function resultsCount(results, type) {
    return results.filter(issue => issue.type == type).length;
}

function getFormattedDate() {
    const date = new Date();
    const formattedDate = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
    return formattedDate;
}

function injectScript() {
    return new Promise((resolve, reject) => {
        const script = document.createElement('script');
        script.src = "https://squizlabs.github.io/HTML_CodeSniffer/build/HTMLCS.js";
        script.addEventListener('load', resolve);
        script.addEventListener('error', e => reject(e.error));
        document.head.appendChild(script);
    });
}

function runHtmlCS(standard, codes) {
    return new Promise(function (resolve, reject) {
        window.HTMLCS.process(standard, window.document, function (error) {
            if (error) {
                return reject(error);
            }
            resolve(window.HTMLCS.getMessages().filter(item => !codes.includes(item.code)).map(processIssue));
        });
    });
}

function processIssue(issue) {
    return {
        type: issue.type,
        code: issue.code,
        techniques: techniques(issue.code),
        msg: issue.msg,
        tag: issue.element.nodeName.toLowerCase(),
        element: htmlElement(issue.element)
    };
}

function techniques(code) {
    if (code.includes('Section508')) {
        const split = code.split('.', 3);
        const para = split[1].toLowerCase();
        return ['1194.22 (' + para + ')'];
    }
    let result = code.match(/([A-Z]+[0-9]+(,[A-Z]+[0-9]+)*)/g) || [];
    if (result.length <= 1)
        return [];
    let list = result[1].split(',').map(linkTechnique);
    return list;
}

function linkTechnique(technique) {
    //https://github.com/squizlabs/HTML_CodeSniffer/blob/aebdff845441ee99252a80d45d65f4ac27f998d7/Standards/WCAG2AAA/ruleset.js
    let prefix = '';
    if (technique.startsWith('ARIA')) {
        prefix = 'aria/';
    } else if (technique.startsWith('SCR')) {
        prefix = 'client-side-script/';
    } else if (technique.startsWith('C')) {
        prefix = 'css/';
    } else if (technique.startsWith('FLASH')) {
        prefix = 'flash/';
    } else if (technique.startsWith('F')) {
        prefix = 'failures/';
    } else if (technique.startsWith('G')) {
        prefix = 'general/';
    } else if (technique.startsWith('H')) {
        prefix = 'html/';
    } else if (technique.startsWith('PDF')) {
        prefix = 'pdf/';
    } else if (technique.startsWith('SVR')) {
        prefix = 'server-side-script/';
    } else if (technique.startsWith('SL')) {
        prefix = 'silverlight/';
    } else if (technique.startsWith('SM')) {
        prefix = 'smil/';
    } else if (technique.startsWith('T')) {
        prefix = 'text/';
    }
    return 'https://www.w3.org/WAI/WCAG21/Techniques/' + prefix + technique;
}

function htmlElement(ele) {
    let a = "";
    if (ele.outerHTML) {
        const o = ele.cloneNode(!0);
        o.innerHTML = "...";
        a = o.outerHTML
    }
    return a;
}

function getBrowser() {
    const userAgent = navigator.userAgent;
    let browser = "Unkown";
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
    const width = window.innerWidth;
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