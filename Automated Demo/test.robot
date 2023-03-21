# Run code in terminal: robot test.robot

*** Settings ***
Library    RPA.Browser.Playwright
Library    ExcelLibrary

*** Variables ***
${btnHome}      (//span[text()="หน้าแรก"])[2]

*** Test Cases ***
# test pantip
#     Open pantip and search pantip   ${btnHome}

test Assignment
    Open pantip and collect comment     ${comment}

*** Keywords ***
Open pantip and search pantip
    [Arguments]     ${xpath}
    Open Browser    https://www.pantip.com/     chromium
    ${status}   Run Keyword And Return Status   Wait For Elements State     ${xpath}      visible
    Log To Console  ${status}
    ${lbHome}   Get Text     ${xpath}
    Open Excel Document    test.xlsx     123
    Write Excel Cell    1   1   ${lbHome}
    Save Excel Document     test.xlsx
    Close All Excel Documents

    # Fill Text       id=search-box      รถยนต์ขึ้นสนิม
    # Press Keys      id=search-box      Enter
    
    Take Screenshot
    Click   //h2[text()="รัชดา"]
    Sleep   10


Open pantip and collect comment
    [Arguments]     ${xpath}
    Open Browser    https://pantip.com/topic/41780470     chromium
    ${status}   Run Keyword And Return Status   Wait For Elements State     ${xpath}      visible
    Log To Console  ${status}
    ${lbHome}   Get Text     ${xpath}
    Open Excel Document    test.xlsx     123
    Write Excel Cell    1   1   ${lbHome}
    Save Excel Document     test.xlsx
    Close All Excel Documents

    # Fill Text       id=search-box      รถยนต์ขึ้นสนิม
    # Press Keys      id=search-box      Enter
    
    Take Screenshot
    Click   //h2[text()="รัชดา"]
    Sleep   10

    Close Browser