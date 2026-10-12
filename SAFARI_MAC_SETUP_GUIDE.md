# Safari Configuration Guide for Mac Automation

## Error: "Session timed out while connecting to Safari instance"

This error occurs when Safari is not properly configured for automation on Mac. Follow these steps to fix it.

---

## Quick Fix Steps

### 1. Enable Safari Remote Automation
1. Open **Safari** browser
2. Go to **Safari** menu → **Settings** (or **Preferences** on older macOS)
3. Click **Advanced** tab
4. ✅ Check **"Show Develop menu in menu bar"**
5. Close Settings
6. Go to **Develop** menu → ✅ Check **"Allow Remote Automation"**

### 2. Trust safaridriver
Run this command in Terminal:
```bash
sudo safaridriver --enable
```
Enter your password when prompted.

### 3. Kill Existing safaridriver Processes
```bash
pkill -9 safaridriver
killall Safari
```

### 4. Verify safaridriver
```bash
safaridriver --version
```
Should show: `Included with Safari 17.x.x`

---

## Detailed Troubleshooting

### Issue: "Request to pair with an automation session" Timeout

**Cause:** Safari is not responding to automation requests quickly enough.

**Solutions:**

#### A. Restart Safari and safaridriver
```bash
# Kill all Safari and safaridriver processes
killall Safari
pkill -9 safaridriver

# Wait 5 seconds
sleep 5

# Enable safaridriver again
sudo safaridriver --enable
```

#### B. Check Safari Security Settings
1. Open **System Settings** → **Privacy & Security**
2. Go to **Automation**
3. Ensure **Terminal** or your IDE (like IntelliJ/Eclipse) is allowed to control Safari

#### C. Grant Full Disk Access (macOS 14+)
1. Open **System Settings** → **Privacy & Security**
2. Go to **Full Disk Access**
3. Add and enable:
   - **Terminal**
   - **Your IDE** (IntelliJ IDEA, Eclipse, etc.)
   - **Java** (if listed)

---

## Framework Code Updates (Already Applied)

### ✅ Added Safari Options Configuration
```java
private SafariOptions getSafariBrowserOptions() {
    SafariOptions options = new SafariOptions();
    options.setUseTechnologyPreview(false);
    options.setAutomaticInspection(false);
    options.setAutomaticProfiling(false);
    return options;
}
```

### ✅ Added Retry Logic for Driver Initialization
- Retries 3 times with 3-second delays
- Better error messages
- Graceful failure handling

---

## Alternative: Use Chrome Instead of Safari on Mac

If Safari continues to have issues, use Chrome:

### Update testng.xml:
```xml
<parameter name="browser" value="chrome"/>
```

### Or in config.properties:
```properties
browser = chrome
```

---

## Common Mac Safari Issues

### Issue 1: Safari Technology Preview Conflicts
**Symptom:** Safari opens but doesn't respond to commands

**Solution:**
```bash
# Uninstall Safari Technology Preview if installed
# Or set useTechnologyPreview to false (already done in code)
```

### Issue 2: Multiple Safari Versions
**Symptom:** Wrong Safari version launches

**Solution:**
```bash
# Check Safari version
/Applications/Safari.app/Contents/MacOS/Safari --version

# Ensure safaridriver matches Safari version
safaridriver --version
```

### Issue 3: Mac Security Prompts
**Symptom:** "Do you want to allow this application to control Safari?" appears

**Solution:**
- Click **OK** or **Allow** when prompted
- The permission is saved after first approval
- Rerun the test after approving

### Issue 4: Firewall Blocking
**Symptom:** Connection timeout errors

**Solution:**
1. Open **System Settings** → **Network** → **Firewall**
2. Click **Options**
3. Add **safaridriver** to allowed applications

---

## Mac-Specific Safari Limitations

⚠️ **Known Safari/Mac Limitations:**
1. **Slower startup** than Chrome/Firefox (3-5 seconds more)
2. **No headless mode** available
3. **Requires GUI session** (can't run in SSH without display)
4. **File downloads** may require additional permissions
5. **Pop-up handling** is stricter than other browsers

---

## Verification Test

Run this simple test to verify Safari setup:

```bash
# From your project directory
mvn clean test -Dtest=LoginTest -Dbrowser=safari
```

If successful, you should see:
```
Safari options configured for Mac compatibility
Running tests locally using safari
```

---

## Still Having Issues?

### Check These:

1. **macOS Version Compatibility**
   ```bash
   sw_vers
   ```
   - macOS 12 (Monterey) or later recommended
   - Safari 15+ required

2. **Java Version**
   ```bash
   java -version
   ```
   - Java 11 or later required
   - Java 17 recommended (currently using 17.0.8.1 ✓)

3. **Selenium Version**
   Check `pom.xml` for Selenium version
   - Selenium 4.18+ recommended (currently using 4.18.1 ✓)

4. **Safari Driver Location**
   ```bash
   which safaridriver
   ```
   Should be: `/usr/bin/safaridriver`

---

## Recommended: Use Chrome for Mac Development

For better reliability on Mac, consider using Chrome:

### Advantages:
- ✅ Faster startup
- ✅ Better automation support
- ✅ Headless mode available
- ✅ More consistent behavior
- ✅ Better error messages

### Setup (if Chrome not installed):
```bash
# Install Chrome
brew install --cask google-chrome

# Install ChromeDriver (auto-managed by Selenium 4)
# No manual installation needed
```

### Update your test configuration:
```properties
# In config.properties
browser = chrome
```

---

## Emergency Workaround

If you need to run tests immediately and Safari won't work:

### Option 1: Use Chrome Grid
```properties
# config.properties
runOnGrid = false
browser = chrome
```

### Option 2: Use Firefox
```bash
# Install Firefox
brew install --cask firefox

# Update config
browser = firefox
```

---

## Support Commands

### Reset Safari Completely
```bash
# Close Safari
killall Safari

# Remove Safari preferences (backup first!)
mv ~/Library/Preferences/com.apple.Safari.plist ~/Desktop/Safari-backup.plist

# Remove Safari cache
rm -rf ~/Library/Caches/com.apple.Safari

# Restart Safari and re-enable automation
```

### Check Running Processes
```bash
# Check if safaridriver is running
ps aux | grep safaridriver

# Check if Safari is running
ps aux | grep Safari
```

### View safaridriver Logs
```bash
# Run safaridriver manually to see logs
safaridriver --port 4444
```

---

## Framework Configuration Summary

The framework has been updated with:

1. ✅ **Safari-specific options** for Mac compatibility
2. ✅ **Retry logic** (3 attempts with 3-second delays)
3. ✅ **Better error messages** showing retry attempts
4. ✅ **Automatic timeout handling** for Safari session creation
5. ✅ **Modern Duration API** for all timeouts

---

## Next Steps

1. Follow **Quick Fix Steps** above
2. Run verification test
3. If Safari still fails, switch to Chrome
4. Report any new errors with full stack trace

---

**Last Updated:** 2026-05-20  
**Framework Version:** 1.1  
**Tested on:** macOS 15.7.3 with Safari 17.x
