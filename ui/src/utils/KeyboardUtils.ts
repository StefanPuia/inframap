export const onEnter = (handle: (value?: string) => Promise<void>) => {
  return (e: KeyboardEvent) => {
    if (e.code === 'Enter') {
      handle((e.target as any)?.value)
        .then(async () => {
          (e as any).target.value = '';
        })
        .catch(console.error);
    }
  }
}