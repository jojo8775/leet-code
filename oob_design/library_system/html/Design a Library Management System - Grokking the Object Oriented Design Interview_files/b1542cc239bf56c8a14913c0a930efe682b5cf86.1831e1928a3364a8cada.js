(window.webpackJsonp_N_E=window.webpackJsonp_N_E||[]).push([[84],{"/0+H":function(e,t,n){"use strict";t.__esModule=!0,t.isInAmpMode=o,t.useAmp=function(){return o(i.default.useContext(a.AmpStateContext))};var r,i=(r=n("q1tI"))&&r.__esModule?r:{default:r},a=n("lwAK");function o(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{},t=e.ampFirst,n=void 0!==t&&t,r=e.hybrid,i=void 0!==r&&r,a=e.hasQuery,o=void 0!==a&&a;return n||i&&o}},"/a9y":function(e,t,n){"use strict";var r=n("lwsE"),i=n("W8MJ"),a=n("7W2i"),o=n("a1gu"),c=n("Nsbk");function u(e){var t=function(){if("undefined"===typeof Reflect||!Reflect.construct)return!1;if(Reflect.construct.sham)return!1;if("function"===typeof Proxy)return!0;try{return Date.prototype.toString.call(Reflect.construct(Date,[],(function(){}))),!0}catch(e){return!1}}();return function(){var n,r=c(e);if(t){var i=c(this).constructor;n=Reflect.construct(r,arguments,i)}else n=r.apply(this,arguments);return o(this,n)}}var d=n("TqRt");t.__esModule=!0,t.default=void 0;var l=d(n("q1tI")),s=d(n("8Kt/")),p={400:"Bad Request",404:"This page could not be found",405:"Method Not Allowed",500:"Internal Server Error"};function f(e){var t=e.res,n=e.err;return{statusCode:t&&t.statusCode?t.statusCode:n?n.statusCode:404}}var h=function(e){a(n,e);var t=u(n);function n(){return r(this,n),t.apply(this,arguments)}return i(n,[{key:"render",value:function(){var e=this.props.statusCode,t=this.props.title||p[e]||"An unexpected error has occurred";return l.default.createElement("div",{style:m.error},l.default.createElement(s.default,null,l.default.createElement("title",null,e,": ",t)),l.default.createElement("div",null,l.default.createElement("style",{dangerouslySetInnerHTML:{__html:"body { margin: 0 }"}}),e?l.default.createElement("h1",{style:m.h1},e):null,l.default.createElement("div",{style:m.desc},l.default.createElement("h2",{style:m.h2},t,"."))))}}]),n}(l.default.Component);t.default=h,h.displayName="ErrorPage",h.getInitialProps=f,h.origGetInitialProps=f;var m={error:{color:"#000",background:"#fff",fontFamily:'-apple-system, BlinkMacSystemFont, Roboto, "Segoe UI", "Fira Sans", Avenir, "Helvetica Neue", "Lucida Grande", sans-serif',height:"100vh",textAlign:"center",display:"flex",flexDirection:"column",alignItems:"center",justifyContent:"center"},desc:{display:"inline-block",textAlign:"left",lineHeight:"49px",height:"49px",verticalAlign:"middle"},h1:{display:"inline-block",borderRight:"1px solid rgba(0, 0, 0,.3)",margin:0,marginRight:"20px",padding:"10px 23px 10px 0",fontSize:"24px",fontWeight:500,verticalAlign:"top"},h2:{fontSize:"14px",fontWeight:"normal",lineHeight:"inherit",margin:0,padding:0}}},"8Kt/":function(e,t,n){"use strict";n("lSNA");t.__esModule=!0,t.defaultHead=l,t.default=void 0;var r,i=function(e){if(e&&e.__esModule)return e;if(null===e||"object"!==typeof e&&"function"!==typeof e)return{default:e};var t=d();if(t&&t.has(e))return t.get(e);var n={},r=Object.defineProperty&&Object.getOwnPropertyDescriptor;for(var i in e)if(Object.prototype.hasOwnProperty.call(e,i)){var a=r?Object.getOwnPropertyDescriptor(e,i):null;a&&(a.get||a.set)?Object.defineProperty(n,i,a):n[i]=e[i]}n.default=e,t&&t.set(e,n);return n}(n("q1tI")),a=(r=n("Xuae"))&&r.__esModule?r:{default:r},o=n("lwAK"),c=n("FYa8"),u=n("/0+H");function d(){if("function"!==typeof WeakMap)return null;var e=new WeakMap;return d=function(){return e},e}function l(){var e=arguments.length>0&&void 0!==arguments[0]&&arguments[0],t=[i.default.createElement("meta",{charSet:"utf-8"})];return e||t.push(i.default.createElement("meta",{name:"viewport",content:"width=device-width"})),t}function s(e,t){return"string"===typeof t||"number"===typeof t?e:t.type===i.default.Fragment?e.concat(i.default.Children.toArray(t.props.children).reduce((function(e,t){return"string"===typeof t||"number"===typeof t?e:e.concat(t)}),[])):e.concat(t)}var p=["name","httpEquiv","charSet","itemProp"];function f(e,t){return e.reduce((function(e,t){var n=i.default.Children.toArray(t.props.children);return e.concat(n)}),[]).reduce(s,[]).reverse().concat(l(t.inAmpMode)).filter(function(){var e=new Set,t=new Set,n=new Set,r={};return function(i){var a=!0;if(i.key&&"number"!==typeof i.key&&i.key.indexOf("$")>0){var o=i.key.slice(i.key.indexOf("$")+1);e.has(o)?a=!1:e.add(o)}switch(i.type){case"title":case"base":t.has(i.type)?a=!1:t.add(i.type);break;case"meta":for(var c=0,u=p.length;c<u;c++){var d=p[c];if(i.props.hasOwnProperty(d))if("charSet"===d)n.has(d)?a=!1:n.add(d);else{var l=i.props[d],s=r[d]||new Set;s.has(l)?a=!1:(s.add(l),r[d]=s)}}}return a}}()).reverse().map((function(e,t){var n=e.key||t;return i.default.cloneElement(e,{key:n})}))}function h(e){var t=e.children,n=(0,i.useContext)(o.AmpStateContext),r=(0,i.useContext)(c.HeadManagerContext);return i.default.createElement(a.default,{reduceComponentsToState:f,headManager:r,inAmpMode:(0,u.isInAmpMode)(n)},t)}h.rewind=function(){};var m=h;t.default=m},"BOr+":function(e,t,n){"use strict";n.d(t,"d",(function(){return o})),n.d(t,"a",(function(){return u})),n.d(t,"c",(function(){return d})),n.d(t,"i",(function(){return l})),n.d(t,"h",(function(){return s})),n.d(t,"g",(function(){return p})),n.d(t,"e",(function(){return f})),n.d(t,"b",(function(){return h})),n.d(t,"f",(function(){return m}));var r=n("vOnD"),i=n("zGpi"),a=n("PS9J"),o=r.d.div.withConfig({displayName:"PageContent",componentId:"sc-8tufop-0"})(["width:100%;"," "," "," "," @media (max-width:","){padding:0 16px;"," ","}"],(function(e){return e.isBlog?Object(r.c)(["padding:40px;border:1px solid ",";box-shadow:0 0px 20px 0 rgba(0,0,0,0.1);background:",";"],(function(e){return e.theme.palette.grey[100]}),(function(e){return e.theme.palette.common.white})):Object(r.c)(["max-width:","px;padding:0 40px;margin-bottom:90px;"],e.customMaxWidth?e.customMaxWidth:i.a.int)}),(function(e){return e.lessPadding&&Object(r.c)(["padding:0 24px;@media (max-width:600px){padding:0 16px;}"])}),(function(e){return e.noBottomMargin&&Object(r.c)(["margin-bottom:0px;"])}),(function(e){return e.hasNarrowWidth&&Object(r.c)(["max-width:",";"],i.o.pixels)}),i.g.pixels,(function(e){return e.isBlog&&Object(r.c)(["padding-bottom:24px;"])}),(function(e){return e.decreasePadding&&Object(r.c)(["padding:0 10px;"])})),c=r.d.div.withConfig({displayName:"Page",componentId:"sc-1643uq9-0"})(["flex:1 0 auto;width:100%;display:flex;flex-direction:column;background-color:",";min-height:100%;position:relative;"," "," "," "," "," #related-blogs{width:100%;}"],(function(e){return e.theme.palette.common.white}),(function(e){return e.bringToFront&&Object(r.c)(["z-index:",";"],a.n)}),(function(e){return e.blockDisplay&&Object(r.c)(["display:block !important;"])}),(function(e){return!e.defaultAlignItems&&Object(r.c)(["align-items:center;"])}),(function(e){return e.isShot&&Object(r.c)(["@media (min-width:960px){background-color:",";"],(function(e){return e.theme.palette.grey[50]}))}),(function(e){return e.hasLightBackground&&Object(r.c)(["background-color:",";"],(function(e){return e.theme.palette.grey[50]}))})),u=Object(r.d)(o).withConfig({displayName:"Page__DynamicContent",componentId:"sc-1643uq9-1"})(["transition:0.2s;"," "," ",""],(function(e){return"small"===e.contentWidth&&Object(r.c)(["max-width:700px;"])}),(function(e){return"medium"===e.contentWidth&&Object(r.c)(["max-width:900px;"])}),(function(e){return"large"===e.contentWidth&&Object(r.c)(["max-width:1024px;"])})),d=r.d.div.withConfig({displayName:"Page__MobileOnly",componentId:"sc-1643uq9-2"})(["",""],(function(e){return Object(r.c)(["@media (min-width:","px){display:none;}"],e.mobileWidth||i.g.int)})),l=r.d.div.withConfig({displayName:"Page__TextBlock",componentId:"sc-1643uq9-3"})(["display:flex;justify-content:center;padding:20px 0 0;color:",";"],(function(e){return e.theme.palette.primary.main})),s=r.d.div.withConfig({displayName:"Page__SidebarPage",componentId:"sc-1643uq9-4"})(["flex:1 1 auto;width:100%;display:flex;flex-direction:row;",""],(function(e){return e.questionVersionPage&&Object(r.c)(["width:calc(100% - 375px);@media (max-width:","){display:none;}"],i.u.pixels)})),p=r.d.div.withConfig({displayName:"Page__ShotPageContent",componentId:"sc-1643uq9-5"})(["padding:56px 16px;margin-bottom:40px;display:flex;box-sizing:border-box;flex-direction:column;flex:inherit;z-index:0;background-color:",";width:760px;max-width:100%;@media (max-width:","){margin-bottom:50px;padding:48px 16px;}@media (min-width:960px){margin-left:24px;padding:56px 40px;max-width:calc(100% - 298px);}@media (min-width:1188px){margin-left:0px;max-width:calc(100% - 568px);}"],(function(e){return e.theme.palette.common.white}),i.o.pixels),f=r.d.div.withConfig({displayName:"Page__PageEditorContent",componentId:"sc-1643uq9-6"})(["width:100%;margin-top:30px;max-width:",";padding:0 40px;margin-bottom:100px;display:flex;flex-direction:column;flex:inherit;z-index:0;"," "," "," @media (max-width:","){padding:0 20px;}"],i.a.pixels,(function(e){return e.fullWidth&&Object(r.c)(["max-width:100%;"])}),(function(e){return e.hasNarrowWidth&&Object(r.c)(["max-width:",";"],i.o.pixels)}),(function(e){return e.centerAlign&&Object(r.c)(["margin-left:auto;margin-right:auto;"])}),i.g.pixels),h=Object(r.d)(c).withConfig({displayName:"Page__FullWidthEditor",componentId:"sc-1643uq9-7"})(["padding:20px 50px;"]),m=Object(r.d)(c).withConfig({displayName:"Page__QuestionViewer",componentId:"sc-1643uq9-8"})(["height:calc(100vh - 139px);overflow:auto;"]);t.j=c},Bnag:function(e,t){e.exports=function(){throw new TypeError("Invalid attempt to spread non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}},EbDI:function(e,t){e.exports=function(e){if("undefined"!==typeof Symbol&&Symbol.iterator in Object(e))return Array.from(e)}},FYa8:function(e,t,n){"use strict";var r;t.__esModule=!0,t.HeadManagerContext=void 0;var i=((r=n("q1tI"))&&r.__esModule?r:{default:r}).default.createContext({});t.HeadManagerContext=i},Ijbi:function(e,t,n){var r=n("WkPL");e.exports=function(e){if(Array.isArray(e))return r(e)}},LixI:function(e,t,n){"use strict";n.r(t);var r=n("o0o1"),i=n.n(r),a=n("rePB"),o=n("HaE+"),c=n("wx14"),u=n("Ff2n"),d=n("q1tI"),l=n.n(d),s=n("eomm"),p=n.n(s),f=n("BMKr"),h=n("IYYG"),m=l.a.createElement;function g(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function x(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?g(Object(n),!0).forEach((function(t){Object(a.a)(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):g(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}var y=function(e){var t=e.statusCode,n=e.hasGetInitialPropsRun,r=e.err,i=Object(u.a)(e,["statusCode","hasGetInitialPropsRun","err"]);return!n&&r&&Object(f.b)(r),404===t?m(h.a,Object(c.a)({key:"*"},i)):m(p.a,{statusCode:t})};y.getInitialProps=function(){var e=Object(o.a)(i.a.mark((function e(t){var n,r;return i.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,p.a.getInitialProps(t);case 2:if(r=e.sent,404!==(null===(n=t.res)||void 0===n?void 0:n.statusCode)){e.next=5;break}return e.abrupt("return",{statusCode:404});case 5:return t.err&&Object(f.b)(t.err),e.abrupt("return",x({hasGetInitialPropsRun:!0},r));case 7:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}(),t.default=y},RIqP:function(e,t,n){var r=n("Ijbi"),i=n("EbDI"),a=n("ZhPi"),o=n("Bnag");e.exports=function(e){return r(e)||i(e)||a(e)||o()}},Xuae:function(e,t,n){"use strict";var r=n("RIqP"),i=n("lwsE"),a=n("W8MJ"),o=(n("PJYZ"),n("7W2i")),c=n("a1gu"),u=n("Nsbk");function d(e){var t=function(){if("undefined"===typeof Reflect||!Reflect.construct)return!1;if(Reflect.construct.sham)return!1;if("function"===typeof Proxy)return!0;try{return Date.prototype.toString.call(Reflect.construct(Date,[],(function(){}))),!0}catch(e){return!1}}();return function(){var n,r=u(e);if(t){var i=u(this).constructor;n=Reflect.construct(r,arguments,i)}else n=r.apply(this,arguments);return c(this,n)}}t.__esModule=!0,t.default=void 0;var l=n("q1tI"),s=function(e){o(n,e);var t=d(n);function n(e){var a;return i(this,n),(a=t.call(this,e))._hasHeadManager=void 0,a.emitChange=function(){a._hasHeadManager&&a.props.headManager.updateHead(a.props.reduceComponentsToState(r(a.props.headManager.mountedInstances),a.props))},a._hasHeadManager=a.props.headManager&&a.props.headManager.mountedInstances,a}return a(n,[{key:"componentDidMount",value:function(){this._hasHeadManager&&this.props.headManager.mountedInstances.add(this),this.emitChange()}},{key:"componentDidUpdate",value:function(){this.emitChange()}},{key:"componentWillUnmount",value:function(){this._hasHeadManager&&this.props.headManager.mountedInstances.delete(this),this.emitChange()}},{key:"render",value:function(){return null}}]),n}(l.Component);t.default=s},eomm:function(e,t,n){e.exports=n("/a9y")},lwAK:function(e,t,n){"use strict";var r;t.__esModule=!0,t.AmpStateContext=void 0;var i=((r=n("q1tI"))&&r.__esModule?r:{default:r}).default.createContext({});t.AmpStateContext=i}}]);
//# sourceMappingURL=b1542cc239bf56c8a14913c0a930efe682b5cf86.1831e1928a3364a8cada.js.map