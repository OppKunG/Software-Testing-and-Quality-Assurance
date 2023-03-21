const Utils = require("./Utils");

Utils.getRandomInt=(max)=>
const getNumber=()=>{
    return Utils.getRandomInt(500)
}

const fn = (impl) =>{
    const mockFn =(...args)=>
    {mockFn.mock.calls.push(args)
        return impl(...args)
    }
    mockFn.mock=
    {calls:[]}
    return mockFn
}

const sum = (a, b) => a + b;

const result = sum(10, 5);
const expected = 15;

const expect = (expected) => {
  return {
    toBe: (actual) => {
      if (actual !== expected) {
        throw new Error({ expected }, "does not equal", { actual });
      }
    },
  };
};
console.log(result);
