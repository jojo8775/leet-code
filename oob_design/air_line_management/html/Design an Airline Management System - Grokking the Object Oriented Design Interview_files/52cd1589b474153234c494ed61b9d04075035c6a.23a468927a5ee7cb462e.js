(window.webpackJsonp_N_E=window.webpackJsonp_N_E||[]).push([[9],{"4mnY":function(t,e,n){"use strict";e.a=function(){return Math.random().toString(36).substr(2,9)}},"Evd+":function(t,e,n){"use strict";n.d(e,"e",(function(){return d})),n.d(e,"b",(function(){return l})),n.d(e,"d",(function(){return p})),n.d(e,"i",(function(){return v})),n.d(e,"j",(function(){return b})),n.d(e,"c",(function(){return O})),n.d(e,"h",(function(){return j})),n.d(e,"g",(function(){return y})),n.d(e,"f",(function(){return E})),n.d(e,"k",(function(){return A})),n.d(e,"a",(function(){return I}));var r=n("KQm4"),a=n("rePB"),o=n("Ff2n"),u=n("i7Pf"),c=n("G384");n("zhJZ");function i(t,e){var n=Object.keys(t);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(t);e&&(r=r.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),n.push.apply(n,r)}return n}function s(t){for(var e=1;e<arguments.length;e++){var n=null!=arguments[e]?arguments[e]:{};e%2?i(Object(n),!0).forEach((function(e){Object(a.a)(t,e,n[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(n)):i(Object(n)).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(n,e))}))}return t}var d={container:{file:{},imageName:"",buildStatus:void 0,buildStatusUrl:"",buildLogUrl:"",liveInstance:{},track:!1},jobs:[],envs:[],version:c.a,loaded:!1},f=function(t){return 0===Object.keys(null!==t&&void 0!==t?t:{}).length},l=function(t){if(0===Object.keys(null!==t&&void 0!==t?t:{}).length)return t;var e,n=t.container,r=(n.liveInstance,n.track,Object(o.a)(n,["liveInstance","track"])),a=t.container.file,u=(a.awaitingUpload,Object(o.a)(a,["awaitingUpload"]));return s(s({},t),{},{container:s(s({},r),{},{file:u}),envs:(e=t.envs,null===e||void 0===e?void 0:e.map((function(t){var e=t.value,n=Object(o.a)(t,["value"]);return n.useAsDefault?s({value:e},n):n})))})},p=function(t){return s(s({},t),{id:t.id||Object(u.d)()})},v=function(t,e){return t.value===e.value&&t.name===e.name&&t.key===e.key},b=function(t){return"UPLOADING"===t},O=function(t){return"FAILURE"===t},j=function(t){return"SUCCESS"===t},y=function(t){return"PENDING"===t},E=function(t){return"FAILURE"===t},A=function(t){return"REBUILDING"===t},I=function(t,e,n){if(f(e)||f(t))return null;var a=t.container,o=a.liveInstance,u=a.imageName,c=t.jobs,i=t.envs;if(e.android)return{android:!0,envs:Object(r.a)(i),job:{runInLiveContainer:!0,startScript:e.startScript},liveInstance:s({},o),imageName:u};if(n)return{imageName:u,job:e,envs:i,liveInstance:o,vnc:!0};if(!u)return null;var d=c.find((function(t){return t.key===e.key}));return f(d)?null:{imageName:u,job:s({},d),envs:Object(r.a)(i),liveInstance:s({},o)}}},G384:function(t,e,n){"use strict";n.d(e,"a",(function(){return r})),n.d(e,"b",(function(){return a}));var r=3,a=function(t){var e,n;if((null===t||void 0===t||null===(e=t.container)||void 0===e?void 0:e.envs)&&(t.envs=t.container.envs,delete t.container.envs),(null===t||void 0===t||null===(n=t.container)||void 0===n?void 0:n.jobs)&&(t.jobs=t.container.jobs,delete t.container.jobs),3!==t.version){var r,a;if(null===t||void 0===t||null===(r=t.container)||void 0===r||null===(a=r.imageName)||void 0===a?void 0:a.includes("/")){var o=t.container.imageName.split("/");if(3!==o.length)throw new Error("Docker image name is invalid");t.container.imageName=o[2]}t.version=3}}},OpYl:function(t,e,n){"use strict";n.d(e,"c",(function(){return i})),n.d(e,"b",(function(){return s})),n.d(e,"a",(function(){return d})),n.d(e,"r",(function(){return f})),n.d(e,"l",(function(){return l})),n.d(e,"k",(function(){return p})),n.d(e,"q",(function(){return v})),n.d(e,"i",(function(){return b})),n.d(e,"j",(function(){return O})),n.d(e,"u",(function(){return j})),n.d(e,"p",(function(){return y})),n.d(e,"t",(function(){return E})),n.d(e,"o",(function(){return A})),n.d(e,"n",(function(){return I})),n.d(e,"s",(function(){return D})),n.d(e,"m",(function(){return h})),n.d(e,"v",(function(){return L})),n.d(e,"g",(function(){return m})),n.d(e,"e",(function(){return P})),n.d(e,"d",(function(){return g})),n.d(e,"h",(function(){return N})),n.d(e,"f",(function(){return S}));var r=n("o0o1"),a=n.n(r),o=n("HaE+"),u=n("fNkK"),c=function(t){return Object(u.a)(t).then(JSON.parse)},i={ALLOCATING:"ALLOCATING",ALLOCATED:"ALLOCATED",ALLOCATION_FAILED:"ALLOCATION_FAILED",WAITING_FOR_DAEMON:"WAITING_FOR_DAEMON",PULLING_IMAGE:"PULLING",IMAGE_PULLED:"PULLED",PULL_FAILED:"PULL_FAILED",DAEMON_FAILED:"DAEMON_FAILED",UPDATING:"UPDATING",UPDATED:"UPDATED",UPDATE_FAILED:"UPDATE_FAILED",PING_APP_FAILED:"PING_APP_FAILED",NO_RESPONSE:"NO_RESPONSE",RUNNING:"RUNNING"},s={ALLOCATION_FAILED:"Resources allocation failed!",PULL_FAILED:"Failed to pull image!",DAEMON_FAILED:"Failed to configure live container!",UPDATE_FAILED:"Failed to update app with with your code!",NO_RESPONSE:"App failed to respond!"},d="educative",f=function(t){return t===i.ALLOCATING},l=function(t){return t===i.ALLOCATED},p=function(t){return t===i.ALLOCATION_FAILED},v=function(t){return t===i.PULLING_IMAGE},b=function(t){return t===i.IMAGE_PULLED},O=function(t){return t===i.PULL_FAILED},j=function(t){return t===i.WAITING_FOR_DAEMON},y=function(t){return t===i.DAEMON_FAILED},E=function(t){return t===i.UPDATING},A=function(t){return t===i.UPDATED},I=function(t){return t===i.UPDATE_FAILED},D=function(t){return t===i.RUNNING},h=function(t){return p(t)||y(t)||I(t)},L=function(t){return!D(t)&&t!==i.UPDATED},m=function(){var t=Object(o.a)(a.a.mark((function t(){var e;return a.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.prev=0,t.next=3,c({url:"/api/instances",type:"GET"});case 3:return e=t.sent,t.abrupt("return",e);case 7:return t.prev=7,t.t0=t.catch(0),t.abrupt("return",{});case 10:case"end":return t.stop()}}),t,null,[[0,7]])})));return function(){return t.apply(this,arguments)}}(),P=function(){var t=Object(o.a)(a.a.mark((function t(e){var n,r,o,u,s,d,f,l;return a.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(n=e.authorId,r=e.workId,o=e.workType,u=e.draft,s=e.containerInfo,t.prev=1,n&&r){t.next=4;break}return t.abrupt("return",{});case 4:return t.next=6,c({url:"/api/instances/".concat(n,"/").concat(r,"/allocate"),type:"POST",data:{work_type:o,draft:u,container_info:JSON.stringify(s)}});case 6:return d=t.sent,f=d.vm_status,l=d.request_id,t.abrupt("return",{instanceStatus:f,requestId:l});case 12:return t.prev=12,t.t0=t.catch(1),t.abrupt("return",{instanceStatus:i.ALLOCATION_FAILED});case 15:case"end":return t.stop()}}),t,null,[[1,12]])})));return function(e){return t.apply(this,arguments)}}(),g=function(){var t=Object(o.a)(a.a.mark((function t(e){var n,r,o,u,s;return a.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(n=e.authorId,r=e.workId,o=e.workType,u=e.draft,t.prev=1,n&&r){t.next=4;break}return t.abrupt("return",{});case 4:return t.next=6,c({url:"/api/instances/".concat(o,"/").concat(n,"/").concat(r,"/allocate-genymotion"),type:"POST",data:{draft:u}});case 6:return s=t.sent,t.abrupt("return",s);case 10:return t.prev=10,t.t0=t.catch(1),t.abrupt("return",{status:i.ALLOCATION_FAILED});case 13:case"end":return t.stop()}}),t,null,[[1,10]])})));return function(e){return t.apply(this,arguments)}}(),N=function(){var t=Object(o.a)(a.a.mark((function t(e){var n,r,o,u;return a.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return n=e.instanceId,r=e.requestId,o={url:"/api/instances/".concat(n,"/image/status?request_id=").concat(r||""),type:"GET"},t.prev=2,t.next=5,c(o);case 5:return u=t.sent,t.abrupt("return",{instanceStatus:u.status});case 9:return t.prev=9,t.t0=t.catch(2),t.abrupt("return",{instanceStatus:i.PULL_FAILED});case 12:case"end":return t.stop()}}),t,null,[[2,9]])})));return function(e){return t.apply(this,arguments)}}(),S=function(){var t=Object(o.a)(a.a.mark((function t(e){var n,r,o,u;return a.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return n=e.instanceId,r=e.requestId,o={url:"/api/instances/".concat(n,"/int-daemon/status?request_id=").concat(r||""),type:"GET"},t.prev=2,t.next=5,c(o);case 5:return u=t.sent,t.abrupt("return",{instanceStatus:u.status});case 9:return t.prev=9,t.t0=t.catch(2),t.abrupt("return",{instanceStatus:i.DAEMON_FAILED});case 12:case"end":return t.stop()}}),t,null,[[2,9]])})));return function(e){return t.apply(this,arguments)}}()},ZfZh:function(t,e,n){"use strict";n.d(e,"b",(function(){return y}));var r=n("o0o1"),a=n.n(r),o=n("HaE+"),u=n("rePB"),c=n("03FM"),i=n("396a"),s=n("zzTb"),d=n("i7Pf"),f=n("fNkK");function l(t,e){var n=Object.keys(t);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(t);e&&(r=r.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),n.push.apply(n,r)}return n}function p(t){for(var e=1;e<arguments.length;e++){var n=null!=arguments[e]?arguments[e]:{};e%2?l(Object(n),!0).forEach((function(e){Object(u.a)(t,e,n[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(n)):l(Object(n)).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(n,e))}))}return t}var v=Object(c.a)(),b=v.initialState,O=v.reducers,j=Object(d.b)({name:"edpresso/shotProperties",initialState:b,reducers:p(p({},O),{},{setBookmarkStatus:function(t,e){var n=e.payload;t.loaded&&(t.data.isBookmarkedByReader=n)},updateAttributions:function(t,e){var n=e.payload;t.data.attributions=n||[]},updateDraftViewers:function(t,e){var n=e.payload;t.data.draftViewers=n||"[]"},setLikeStatus:function(t,e){var n=e.payload,r=n.like,a=n.shotLikes;t.loaded&&(t.data.isLikedByReader=r,t.data.shotLikes=a)}})});e.a=j.reducer;var y=p(p({},j.actions),{},{load:function(t){var e=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];return function(n,r){r().edpresso.properties.loaded||r().edpresso.properties.loading||n(Object(u.a)({},i.a,{types:j.actions,endpoint:"edpresso/shot/".concat(t,"/props"),queryParams:{status:e?"published":"draft"},postProcess:function(t){return Object(s.a)(JSON.parse(t))}}))}},updateShotAttribution:function(t,e){return function(){var n=Object(o.a)(a.a.mark((function n(r){var o;return a.a.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:return n.next=2,Object(f.a)({url:"/api/edpresso/editor/shot/".concat(t,"/update-attributions"),type:"POST",data:{attributions:JSON.stringify(e)}});case 2:return o=n.sent,r(y.updateAttributions(Object(s.a)(e))),n.abrupt("return",o);case 5:case"end":return n.stop()}}),n)})));return function(t){return n.apply(this,arguments)}}()}})},dU21:function(t,e,n){"use strict";n.d(e,"b",(function(){return m}));var r=n("o0o1"),a=n.n(r),o=n("HaE+"),u=n("rePB"),c=n("Vgjn"),i=n("Evd+"),s=n("OpYl"),d=n("03FM"),f=n("396a"),l=n("E891"),p=n("i7Pf"),v=n("740l"),b=n("BMKr"),O=n("ZfZh"),j=n("pLgj");function y(t,e){var n=Object.keys(t);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(t);e&&(r=r.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),n.push.apply(n,r)}return n}function E(t){for(var e=1;e<arguments.length;e++){var n=null!=arguments[e]?arguments[e]:{};e%2?y(Object(n),!0).forEach((function(e){Object(u.a)(t,e,n[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(n)):y(Object(n)).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(n,e))}))}return t}var A="edpresso/shot",I=Object(d.a)(),D=I.initialState,h=I.reducers,L=Object(p.b)({name:A,initialState:D,reducers:E(E({},h),{},{updateReadTime:function(t,e){var n=e.payload;n&&t.loaded&&(t.data.readTime=n)}})});e.a=L.reducer;var m=E(E({},L.actions),{},{clear:function(){return function(t){t(L.actions.clear()),t(O.b.clear())}},load:function(t){var e=t.shot_id,n=t.shot_url,r=void 0===n?void 0:n,i=arguments.length>1&&void 0!==arguments[1]&&arguments[1];return function(){var t=Object(o.a)(a.a.mark((function t(n,o){var s,d,p,O,j,y;return a.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.prev=0,t.next=3,n(Object(u.a)({},f.a,{types:L.actions,endpoint:"".concat(A,"/").concat(null!==e&&void 0!==e?e:"url/".concat(r)),postProcess:l.a,getHeaders:!0}));case 3:if((O=t.sent)&&n(m.updateETag(O.xhr.headers.get(c.a))),!o().edpresso.shots.error){t.next=7;break}throw new Error("SHOT_NOT_LOADED");case 7:if((j=o().edpresso.shots.data)&&i&&n(m.prepareDockerOnSetShot(j)),(null===j||void 0===j?void 0:j.shotId)||i){t.next=11;break}throw new Error("SHOT_CLEARED");case 11:n(v.b.load(null===(s=o().edpresso)||void 0===s||null===(d=s.shots)||void 0===d||null===(p=d.data)||void 0===p?void 0:p.content,!0)),t.next=18;break;case 14:t.prev=14,t.t0=t.catch(0),"SHOT_CLEARED"!==(y=null===t.t0||void 0===t.t0?void 0:t.t0.message)&&"SHOT_NOT_LOADED"!==y&&Object(b.b)(t.t0);case 18:case"end":return t.stop()}}),t,null,[[0,14]])})));return function(e,n){return t.apply(this,arguments)}}()},prepareDockerOnSetShot:function(t){return function(){var e=Object(o.a)(a.a.mark((function e(n,r){var o,u,c,d;return a.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return u=(null===t||void 0===t?void 0:t.docker)?E(E({},t.docker),{},{envs:null!==(o=t.docker.envs)&&void 0!==o?o:[]}):i.e,e.next=3,Object(s.g)();case 3:c=e.sent,d=E(E({},u),{},{container:E(E({},u.container),{},{liveInstance:c})}),n(j.b.setObject(d));case 6:case"end":return e.stop()}}),e)})));return function(t,n){return e.apply(this,arguments)}}()}})},pLgj:function(t,e,n){"use strict";n.d(e,"b",(function(){return O}));var r=n("o0o1"),a=n.n(r),o=n("HaE+"),u=n("rePB"),c=n("Evd+"),i=n("4mnY"),s=n("i7Pf"),d=n("fNkK");function f(t,e){var n=Object.keys(t);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(t);e&&(r=r.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),n.push.apply(n,r)}return n}function l(t){for(var e=1;e<arguments.length;e++){var n=null!=arguments[e]?arguments[e]:{};e%2?f(Object(n),!0).forEach((function(e){Object(u.a)(t,e,n[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(n)):f(Object(n)).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(n,e))}))}return t}var p=c.e,v=function(t,e){for(var n=t,r=0;e.filter((function(t){return t.name===n})).length;){if(++r>20){n="".concat(t,"-").concat(Object(s.d)());break}n="".concat(t,"-").concat(Object(i.a)().substr(0,6))}return n},b=Object(s.b)({name:"dockerDetails",initialState:p,reducers:{setObject:function(t,e){return l(l({},e.payload),{},{loaded:!0})},setFile:function(t,e){var n=e.payload;t.container.imageName="",Object.assign(t.container,n)},removeImage:function(t){t.container=c.e.container},updateBuildStatus:function(t,e){var n=e.payload;t.container&&(t.container.buildStatus=n)},setTrackStatus:function(t,e){var n=e.payload,r=void 0!==n&&n;t.container&&(t.container.track=r)},addJob:function(t,e){var n=e.payload;t.jobs||(t.jobs=[]),t.jobs.push(l(l({},n),{},{key:Object(s.d)()}))},duplicateJob:function(t,e){var n=e.payload;t.jobs||(t.jobs=[]),t.jobs.push(l(l({},n),{},{key:Object(s.d)(),name:v("".concat(n.name,"-copy"),t.jobs)}))},updateJob:function(t,e){var n=e.payload,r=n.payload,a=n.index;t.jobs[a]=r},removeJob:function(t,e){var n=e.payload.index;t.jobs.splice(n,1)},afterFileUpload:function(t,e){var n=e.payload;t.container.imageName=n,t.container.buildStatus="PENDING",t.container.file.awaitingUpload=!1},saveEnvVariable:function(t,e){var n,r=e.payload.env,a=r.id,o=void 0===a?"":a,u=r.key,c=void 0===u?"":u,i=r.value,d=void 0===i?"":i,f=r.useAsDefault,l=void 0===f||f,p=r.deleted,v=void 0!==p&&p,b=r.defaultValue,O=void 0===b?"":b,j=c,y=null!==(n=null===t||void 0===t?void 0:t.envs)&&void 0!==n?n:[],E={id:o||Object(s.d)(),key:c,name:j,value:d,useAsDefault:l,deleted:v,defaultValue:O},A=y.findIndex((function(t){return t.id===o}));-1!==A?t.envs[A]=E:t.envs.push(E)},saveEnvs:function(t,e){var n=e.payload;t.envs=n.envs},removeEnvVariable:function(t,e){var n=e.payload.env,r=t.envs.findIndex((function(t){return t.id===n.id}));-1!==r&&t.envs.splice(r,1)}}});e.a=b.reducer;var O=l(l({},b.actions),{},{rebuildImage:function(t){return function(e){e(O.updateBuildStatus("REBUILDING"));var n=null===t||void 0===t?void 0:t.container;Object(d.a)({url:null===n||void 0===n?void 0:n.rebuildImageUrl,type:"PUT",processData:!1}).then((function(){e(O.updateBuildStatus("PENDING"))})).catch((function(){e(O.updateBuildStatus("FAILURE"))}))}},trackBuild:function(t,e){return function(n,r){var u,i=null===(u=r().dockerDetails)||void 0===u?void 0:u.container;if(i&&i.track&&Object(c.g)(i.buildStatus)){var s=function(){var u=Object(o.a)(a.a.mark((function o(){var u,f,l,p,v,b;return a.a.wrap((function(a){for(;;)switch(a.prev=a.next){case 0:return a.next=2,Object(d.a)({url:i.buildStatusUrl,type:"GET"},!0);case 2:l=a.sent,p=l.data,v=JSON.parse(p),b=v.build_status,(null===(u=r().dockerDetails)||void 0===u||null===(f=u.container)||void 0===f?void 0:f.track)&&Object(c.g)(b)?setTimeout(s,3e3):Object(c.g)(b)||(n(O.setTrackStatus(!1)),n(t(e,O.updateBuildStatus(b))));case 6:case"end":return a.stop()}}),o)})));return function(){return u.apply(this,arguments)}}();setTimeout(s,1e3)}}}})},zhJZ:function(t,e,n){"use strict";n.d(e,"a",(function(){return o})),n.d(e,"c",(function(){return u})),n.d(e,"b",(function(){return c}));var r=function(){try{return localStorage.setItem("test","test"),localStorage.removeItem("test"),!0}catch(t){return!1}},a=n("+Zpp"),o=function(t){return r()?localStorage.getItem(t):null},u=function(t,e){if(r())try{localStorage.setItem(t,e)}catch(n){Object(a.c)(a.b.EXCEPTION,a.a.LOCAL_STORAGE_QUOTA_EXCEDED),localStorage.clear()}},c=function(t){r()&&localStorage.removeItem(t)}},zzTb:function(t,e,n){"use strict";var r=function(t){return Array.isArray(t)};e.a=function t(e){if((a=e)===Object(a)&&!r(a)&&"function"!==typeof a){var n={};return Object.keys(e).forEach((function(r){var a;n[(a=r,a.replace(/([-_][a-z])/gi,(function(t){return t.toUpperCase().replace("-","").replace("_","")})))]=t(e[r])})),n}var a;return r(e)?e.map((function(e){return t(e)})):e}}}]);
//# sourceMappingURL=52cd1589b474153234c494ed61b9d04075035c6a.23a468927a5ee7cb462e.js.map