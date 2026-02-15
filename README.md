<p align="center">
  <picture>
    <source
      width="350px"
      media="(prefers-color-scheme: dark)"
      srcset="Logo/ExtenRe-Red.svg">
    <img src="Logo/ExtenRe-Red-Light.svg" alt="ExtenRe Patcher Logo" width="350px">
  </picture>
</p>

# 🔧 ExtenRe Integrations  

**ExtenRe Integrations** contains classes and modules that integrate with the **ExtenRe** project to extend its functionality. This repository allows the integration of new features and optimizations that can be used alongside the **ExtenRe Patcher**.

## ❓ How to Use Debugging

If you need to debug any integrations or patching processes, you can capture the log output using the following commands:

### Windows:
Use `adb logcat` to capture logs, filtering for the keyword "ExtenRe". This will write the logs to a file called `log.txt`.

```bash
adb logcat | findstr "ExtenRe" > log.txt