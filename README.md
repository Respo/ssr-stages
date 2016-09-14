
Respo SSR stages demo
----

* Client rendering http://repo.tiye.me/Respo/ssr-stages/index.html
* Server side shell rendering http://repo.tiye.me/Respo/ssr-stages/shell.html
* Server side rendering http://repo.tiye.me/Respo/ssr-stages/dynamic.html

### Explanations

* `index.html`: client side...
* `shell.html`: only page shell is rendered on server side
* `dynamic.html`: dynamic content rendered on server side

Also more details on:

* SSR in Respo https://github.com/Respo/respo/wiki/SSR
* Chinese video explanations http://weibo.com/1651843872/E6UXUEZYj
* Chinese guide https://segmentfault.com/a/1190000006903860

### Develop

Build pages locally into `target/`:

```bash
boot build-advanced
export boot_deps=`boot show -c`
planck -c $boot_deps:src/ -i stages-html.cljs
```

Find out more on https://github.com/mvc-works/stack-workflow

### License

MIT
